package com.poc.strategy.impl;

import java.util.logging.Logger;

import com.poc.dto.OrderModel;
import com.poc.strategy.AbstractBookingContactNameStrategy;

public class BookingContactNameStrategyImpl extends AbstractBookingContactNameStrategy {

	private static final Logger LOG = Logger.getLogger(BookingContactNameStrategyImpl.class.getName());

	@Override
	public void add(OrderModel orderModel) {
		LOG.info("Booking Contact Name Params:" + getAttrName());
		LOG.info("Adding value:" + getValue());
	}

	@Override
	public void update(OrderModel orderModel) {
		LOG.info("Booking Contact Name Params:" + getAttrName());
		LOG.info("Updating value:" + getValue());
	}

	@Override
	public void delete(OrderModel orderModel) {
		LOG.info("Booking Contact Name Params:" + getAttrName());
		LOG.info("Deleting value:" + getValue());
	}
}
