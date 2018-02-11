package com.poc.strategy.impl;

import java.util.logging.Logger;

import com.poc.dto.OrderModel;
import com.poc.strategy.AbstractPassengerDetailsNameStrategy;

public class PassengerDetailsNameStrategyImpl extends AbstractPassengerDetailsNameStrategy {

	private static final Logger LOG = Logger.getLogger(PassengerDetailsNameStrategyImpl.class.getName());

	@Override
	public void add(OrderModel orderModel) {
		LOG.info("Pax Details Params:" + getFlightKey() + "--" + getPaxId() + "--" + getAttrName());
		LOG.info("Adding value:" + getValue());
	}

	@Override
	public void update(OrderModel orderModel) {
		LOG.info("Pax Details Params:" + getFlightKey() + "--" + getPaxId() + "--" + getAttrName());
		LOG.info("Updating value:" + getValue());
	}

	@Override
	public void delete(OrderModel orderModel) {
		LOG.info("Pax Details Params:" + getFlightKey() + "--" + getPaxId() + "--" + getAttrName());
		LOG.info("Deleting value:" + getValue());
	}

}
