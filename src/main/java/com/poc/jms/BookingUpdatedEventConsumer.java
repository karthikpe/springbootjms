package com.poc.jms;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.poc.event.airport.booking.update.dto.BookingUpdatedAtAirportDTO;
import com.poc.processor.AbstractEventProcessor;
import com.poc.utils.JsonUtils;

@Component
public class BookingUpdatedEventConsumer {

	private static final Logger LOG = Logger.getLogger(BookingUpdatedEventConsumer.class.getName());
	
	@Autowired
	AbstractEventProcessor<BookingUpdatedAtAirportDTO> processor;

	@JmsListener(destination = "${poc.queue}")
	public void receiveQueue(BookingUpdatedAtAirportDTO eventDto) {
		LOG.info("Booking updated event data:" + JsonUtils.getJson(eventDto));
		processor.processEventMessage(eventDto);
	}
}
