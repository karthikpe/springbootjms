package com.poc.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.poc.dto.OrderModel;
import com.poc.strategy.EventStrategy;
import com.poc.utils.JsonUtils;

public abstract class AbstractEventProcessor<EventDto extends Object> {

	private static final Logger LOG = Logger.getLogger(AbstractEventProcessor.class.getName());

	private static String SCHEMA_OP = "op";
	private static String SCHEMA_PATH = "path";
	private static String SCHEMA_VALUE = "value";

	private static String OPERATION_ADD = "add";
	private static String OPERATION_REPLACE = "replace";
	private static String OPERATION_REMOVE = "remove";

	private static Integer DEFAULT_PRIORITY_VALUE = 100;

	@Resource(name = "patternStrategyMap")
	Map<String, EventStrategy> patternStrategyMap;

	@Resource(name = "addStrategiesPriority")
	Map<EventStrategy, Integer> addStrategiesPriority;

	@Resource(name = "replaceStrategiesPriority")
	Map<EventStrategy, Integer> replaceStrategiesPriority;

	@Resource(name = "removeStrategiesPriority")
	Map<EventStrategy, Integer> removeStrategiesPriority;

	
	public abstract void processEventMessage(EventDto eventDto);

	public OrderModel getOrder(String bookingReference) {
		LOG.info("Order number:" + bookingReference);
		return new OrderModel();
	}

	public void processOperations(OrderModel orderModel, Set<Object> operations) {

		Map<String, List<EventStrategy>> oprStrategiesMap = initializeStrategies(operations);

		processStrategies(orderModel, oprStrategiesMap.get(OPERATION_ADD), getPriorityMap(OPERATION_ADD));
		processStrategies(orderModel, oprStrategiesMap.get(OPERATION_REPLACE), getPriorityMap(OPERATION_REPLACE));
		processStrategies(orderModel, oprStrategiesMap.get(OPERATION_REMOVE), getPriorityMap(OPERATION_REMOVE));
	}

	protected Map<String, List<EventStrategy>> initializeStrategies(Set<Object> operations) {
		Map<String, List<EventStrategy>> oprStrategyMap = new HashMap<>();
		oprStrategyMap.put(OPERATION_ADD, new ArrayList<>());
		oprStrategyMap.put(OPERATION_REPLACE, new ArrayList<>());
		oprStrategyMap.put(OPERATION_REMOVE, new ArrayList<>());

		for (Object opr : operations) {
			final String oprStr = JsonUtils.getJson(opr);
			final JsonNode oprNode = JsonUtils.getJsonNode(oprStr);
			String path = JsonUtils.getValue(oprNode, SCHEMA_PATH);

			EventStrategy matchingStrategy = getMatchingStrategy(path);
			if (matchingStrategy == null) {
				LOG.warning("Strategy not found for path " + path);
				continue;
			}
			JsonNode value = JsonUtils.getObject(oprNode, SCHEMA_VALUE);
			matchingStrategy.initialize(path, value);

			String op = JsonUtils.getValue(oprNode, SCHEMA_OP);
			oprStrategyMap.get(op).add(matchingStrategy);
		}
		return oprStrategyMap;
	}

	private Map<EventStrategy, Integer> getPriorityMap(String operation) {
		if (OPERATION_ADD.equals(operation)) {
			return addStrategiesPriority;
		} else if (OPERATION_REPLACE.equals(operation)) {
			return replaceStrategiesPriority;
		} else if (OPERATION_REMOVE.equals(operation)) {
			return removeStrategiesPriority;
		}
		return Collections.emptyMap();
	}

	private void processStrategies(OrderModel orderModel, List<EventStrategy> eventStrategies,
			Map<EventStrategy, Integer> strategiesPriorityMap) {
		eventStrategies.sort(Comparator.comparing(key -> getStrategyPriority(key, strategiesPriorityMap)));
		eventStrategies.forEach(strategy -> strategy.add(orderModel));
	}

	private Integer getStrategyPriority(EventStrategy key, Map<EventStrategy, Integer> strategiesPriorityMap) {
		return strategiesPriorityMap.getOrDefault(key, DEFAULT_PRIORITY_VALUE);
	}

	private EventStrategy getMatchingStrategy(String path) {
		for (String pattern : patternStrategyMap.keySet()) {
			if (Pattern.matches(pattern, path)) {
				return patternStrategyMap.get(pattern);
			}
		}
		return null;
	}
}
