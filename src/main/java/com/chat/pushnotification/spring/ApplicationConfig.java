package com.chat.pushnotification.spring;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ApplicationConfig {
	@Bean
	public ObjectMapper geObjectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public HttpClient getHttpClient() {
		return HttpClients.createDefault();
	}
}
