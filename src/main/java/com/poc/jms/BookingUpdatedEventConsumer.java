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
	public void receiveQueue(String json) {
		LOG.info("Booking updated event consumer data:" + json);
		BookingUpdatedAtAirportDTO eventDto = JsonUtils.convertToDto(json, BookingUpdatedAtAirportDTO.class);
		processor.processEventMessage(eventDto);
	}
}
