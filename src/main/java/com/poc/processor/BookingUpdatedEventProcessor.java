package com.poc.processor;

import org.springframework.stereotype.Service;

import com.poc.dto.OrderModel;
import com.poc.event.airport.booking.update.dto.BookingUpdatedAtAirportDTO;

@Service
public class BookingUpdatedEventProcessor extends AbstractEventProcessor<BookingUpdatedAtAirportDTO> {

	@Override
	public void processEventMessage(BookingUpdatedAtAirportDTO eventDto) {
		OrderModel orderModel = getOrder(eventDto.getBody().getBookingReference());
		processOperations(orderModel, eventDto.getBody().getOperations());
		// modelService.save(orderModel);
	}
}
