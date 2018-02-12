package com.poc.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.poc.event.airport.booking.update.dto.BookingUpdatedAtAirportDTO;

@Component
public class Sender {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String destination, BookingUpdatedAtAirportDTO message) {
		jmsTemplate.convertAndSend(destination, message);
	}
}
