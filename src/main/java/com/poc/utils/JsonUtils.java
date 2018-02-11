package com.poc.utils;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author "k.ekambaram"
 *
 */
public class JsonUtils {

	private static final Logger LOG = Logger.getLogger(JsonUtils.class);

	public static String getJson(final Object dataObj) {
		final ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(dataObj);
		} catch (final JsonProcessingException e) {
			LOG.error(e.getMessage());
		}
		return json;
	}

	public static <T extends Object> T convertToDto(String json, Class<T> t) {
		final ObjectMapper mapper = new ObjectMapper();
		final JsonNode node = getJsonNode(json);
		try {
			return mapper.readValue(node.toString(), t);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return null;
	}

	public static JsonNode getJsonNode(final String json) {
		final ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = mapper.readTree(json);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return jsonNode;
	}

	public static String getValueAtPath(final JsonNode node, final String path) {
		return (node.at(path) != null) ? node.at(path).asText() : null;
	}

	public static String getValue(final JsonNode node, final String fieldName) {
		return (node.get(fieldName) != null) ? node.get(fieldName).asText() : null;
	}

	public static JsonNode getObject(final JsonNode node, final String fieldName) {
		return node.get(fieldName);
	}
}
