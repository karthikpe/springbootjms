package com.poc.processor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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

		Map<String, List<EventStrategy>> oprStrategiesMap = initialize(operations);

		List<EventStrategy> addStrategies = oprStrategiesMap.get(OPERATION_ADD);
		List<EventStrategy> replaceStrategies = oprStrategiesMap.get(OPERATION_REPLACE);
		List<EventStrategy> removeStrategies = oprStrategiesMap.get(OPERATION_REMOVE);

		addStrategies.sort(Comparator.comparing(key -> getStrategyPriority(key, addStrategiesPriority)));
		addStrategies.forEach(strategy -> strategy.add(orderModel));

		replaceStrategies.sort(Comparator.comparing(key -> getStrategyPriority(key, replaceStrategiesPriority)));
		replaceStrategies.forEach(strategy -> strategy.update(orderModel));

		removeStrategies.sort(Comparator.comparing(key -> getStrategyPriority(key, removeStrategiesPriority)));
		removeStrategies.forEach(strategy -> strategy.delete(orderModel));
	}

	private Integer getStrategyPriority(EventStrategy key, Map<EventStrategy, Integer> strategiesPriorityMap) {
		return strategiesPriorityMap.getOrDefault(key, DEFAULT_PRIORITY_VALUE);
	}

	private Map<String, List<EventStrategy>> initialize(Set<Object> operations) {

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

	private EventStrategy getMatchingStrategy(String path) {
		for (String pattern : patternStrategyMap.keySet()) {
			if (Pattern.matches(pattern, path)) {
				return patternStrategyMap.get(pattern);
			}
		}
		return null;
	}
}
