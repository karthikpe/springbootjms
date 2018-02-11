package com.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.event.airport.booking.update.dto.BookingUpdatedAtAirportDTO;
import com.poc.processor.AbstractEventProcessor;

@RestController
public class BookingUpdatedEventController {

	@Autowired
	AbstractEventProcessor<BookingUpdatedAtAirportDTO> processor;

	@RequestMapping(value = "/event", method = RequestMethod.POST)
	void processEvent(@RequestBody final BookingUpdatedAtAirportDTO eventDto) {
		processor.processEventMessage(eventDto);
	}

}
