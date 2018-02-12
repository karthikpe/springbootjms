package com.poc.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class JmsListenerConfig implements JmsListenerConfigurer {

	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
		registrar.setMessageHandlerMethodFactory(handlerMethodFactory());
	}

	@Bean
	public DefaultMessageHandlerMethodFactory handlerMethodFactory() {
		DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
		factory.setMessageConverter(messageConverter());
		return factory;
	}

	@Bean
	public MessageConverter messageConverter() {
		return new MappingJackson2MessageConverter();
	}

}
