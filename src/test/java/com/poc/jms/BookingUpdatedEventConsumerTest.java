package com.poc.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.poc.event.airport.booking.update.dto.BookingUpdatedAtAirportDTO;
import com.poc.utils.JsonUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingUpdatedEventConsumerTest {

	@Autowired
	private Sender sender;

	@Test
	public void testSendAndReceiveData() {
		String json = createALEventData();
		BookingUpdatedAtAirportDTO eventDto = JsonUtils.convertToDto(json, BookingUpdatedAtAirportDTO.class);
		System.out.println("Sending json data to queue poc.event");
		sender.send("poc.event", eventDto);
	}

	private String createALEventData() {
		final StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("\"header\": {");
		sb.append("\"messageName\": \"bookingUpdated_event\",");
		sb.append("\"messageVersion\": \"1.0.0\",");
		sb.append("\"messageGenerationTimestamp\": \"2012-04-23T18:25:43.511Z\",");
		sb.append("\"messageProducerName\": \"ACP\",");
		sb.append("\"operationType\": \"UPDATE\"");
		sb.append("},");
		sb.append("\"body\": {");
		sb.append("\"bookingReference\": \"FRNQ3HD\",");
		sb.append("\"operations\": [");
		sb.append("{");
		sb.append("\"op\": \"add\",");
		sb.append("\"path\": \"/body/flights/20121024LISFNC7609/passengers/87654325/comments/897\",");
		sb.append("\"value\": {");
		sb.append("\"commentCode\": \"897\",");
		sb.append("\"commentText\": \"Voluntary Offload\",");
		sb.append("\"userId\": 12345,");
		sb.append("\"userFirstName\": \"John\",");
		sb.append("\"userSurname\": \"Smiths\",");
		sb.append("\"eResLocationCode\": \"LTN\"");
		sb.append("}");
		sb.append("},");
		sb.append("{ ");
		sb.append("\"op\": \"replace\",");
		sb.append("\"path\": \"/body/outbounds/flights/20121024LISFNC7609/passengers/87654325/comments/897\",");
		sb.append("\"value\": {");
		sb.append("\"commentCode\": \"897\",");
		sb.append("\"commentText\": \"Voluntary Offload\",");
		sb.append("\"userId\": null,");
		sb.append("\"rowStatus\": \"Deleted\"");
		sb.append("}");
		sb.append("},");
		sb.append("{ ");
		sb.append("\"op\": \"replace\",");
		sb.append("\"path\": \"/body/flights/20121024LISFNC7609/passengers/87654325/passengerDetails/name/title\",");
		sb.append("\"value\": \"Mr\"");
		sb.append("},");
		sb.append("{ ");
		sb.append("\"op\": \"replace\",");
		sb.append(
				"\"path\": \"/body/flights/20121024LISFNC7609/passengers/87654325/passengerDetails/name/firstName\",");
		sb.append("\"value\": \"Joe\"");
		sb.append("},");
		sb.append("{ ");
		sb.append("\"op\": \"replace\",");
		sb.append("\"path\": \"/body/flights/error\",");
		sb.append("\"value\": \"Joe\"");
		sb.append("},");
		sb.append("{ ");
		sb.append("\"op\": \"replace\",");
		sb.append("\"path\": \"/body/bookingContact/name/lastName\",");
		sb.append("\"value\": \"Smith\"");
		sb.append("},");
		sb.append("{ ");
		sb.append("\"op\": \"replace\",");
		sb.append("\"path\": \"/body/bookingContact/address/addressLine2\",");
		sb.append("\"value\": null");
		sb.append("}");
		sb.append("]");
		sb.append("}");
		sb.append("}");

		return sb.toString();
	}
}
