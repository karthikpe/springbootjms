package com.poc.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.event.airport.booking.update.dto.BookingUpdatedAtAirportDTO;
import com.poc.processor.AbstractEventProcessor;
import com.poc.utils.JsonUtils;

@RestController
public class BookingUpdatedEventController {

	private static final Logger LOG = Logger.getLogger(BookingUpdatedEventController.class.getName());
	
	@Autowired
	AbstractEventProcessor<BookingUpdatedAtAirportDTO> processor;

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	void processEvent(@RequestBody final BookingUpdatedAtAirportDTO eventDto) {
		LOG.info("Booking updated event data:" + JsonUtils.getJson(eventDto));
		processor.processEventMessage(eventDto);
	}

}
