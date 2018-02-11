package com.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:services-spring.xml")
public class EventConsumerPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventConsumerPocApplication.class, args);
	}

}
