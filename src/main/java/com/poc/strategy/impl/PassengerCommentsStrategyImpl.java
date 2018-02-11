package com.poc.strategy.impl;

import java.util.logging.Logger;

import com.poc.dto.OrderModel;
import com.poc.strategy.AbstractPassengerCommentsStrategy;
import com.poc.utils.JsonUtils;

public class PassengerCommentsStrategyImpl extends AbstractPassengerCommentsStrategy {

	private static final Logger LOG = Logger.getLogger(PassengerCommentsStrategyImpl.class.getName());

	@Override
	public void add(OrderModel orderModel) {
		LOG.info("Pax Comments Params:" + getFlightKey() + "--" + getPaxId() + "--" + getAttrName());
		LOG.info("Adding value:" + JsonUtils.getJson(getValue()));
	}

	@Override
	public void update(OrderModel orderModel) {
		LOG.info("Pax Comments Params:" + getFlightKey() + "--" + getPaxId() + "--" + getAttrName());
		LOG.info("Updating value:" + JsonUtils.getJson(getValue()));
	}

	@Override
	public void delete(OrderModel orderModel) {
		LOG.info("Pax Comments Params:" + getFlightKey() + "--" + getPaxId() + "--" + getAttrName());
		LOG.info("Deleting value:" + JsonUtils.getJson(getValue()));
	}

}
