package com.poc.processor;

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
	
	private static String OPERATION_CREATE = "CREATE";
	private static String OPERATION_ADD = "ADD";
	private static String OPERATION_UPDATE = "UPDATE";
	private static String OPERATION_REPLACE = "REPLACE";
	private static String OPERATION_DELETE = "DELETE";
	private static String OPERATION_REMOVE = "REMOVE";
	
	@Resource(name = "patternStrategyMap")
	Map<String, EventStrategy> patternStrategyMap;

	public abstract void processEventMessage(EventDto eventDto);

	public OrderModel getOrder(String bookingReference) {
		LOG.info("Order number:" + bookingReference);
		return new OrderModel();
	}

	public void processOperations(OrderModel orderModel, Set<Object> operations) {
		for (Object opr : operations) {
			final String oprStr = JsonUtils.getJson(opr);
			final JsonNode oprNode = JsonUtils.getJsonNode(oprStr);
			String path = JsonUtils.getValue(oprNode, SCHEMA_PATH);
			
			EventStrategy matchingStrategy = getMatchingStrategy(path);
			if (matchingStrategy == null) {
				continue;
			}
			String op = JsonUtils.getValue(oprNode, SCHEMA_OP);
			JsonNode value = JsonUtils.getObject(oprNode, SCHEMA_VALUE);
			
			matchingStrategy.initialize(path, value);
			performOperation(orderModel, op, matchingStrategy, value);
		}
	}

	private void performOperation(OrderModel orderModel, String operation, EventStrategy strategy,
			JsonNode value) {
		if (operation.equalsIgnoreCase(OPERATION_CREATE) || operation.equalsIgnoreCase(OPERATION_ADD)) {
			strategy.add(orderModel);
		} else if (operation.equalsIgnoreCase(OPERATION_UPDATE) || operation.equalsIgnoreCase(OPERATION_REPLACE)) {
			strategy.update(orderModel);
		} else if (operation.equalsIgnoreCase(OPERATION_DELETE) || operation.equalsIgnoreCase(OPERATION_REMOVE)) {
			strategy.delete(orderModel);
		} else {
			LOG.warning("Unknown operation");
		}

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
