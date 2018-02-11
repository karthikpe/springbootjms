package com.poc.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.poc.event.airport.booking.update.dto.BookingUpdatedAtAirportDTO;
import com.poc.processor.AbstractEventProcessor;
import com.poc.utils.JsonUtils;

@Component
public class BookingUpdatedEventConsumer {

	@Autowired
	AbstractEventProcessor<BookingUpdatedAtAirportDTO> processor;

	@JmsListener(destination = "${poc.queue}")
	public void receiveQueue(String json) {
		BookingUpdatedAtAirportDTO eventDto = JsonUtils.convertToDto(json, BookingUpdatedAtAirportDTO.class);
		processor.processEventMessage(eventDto);
	}
}
