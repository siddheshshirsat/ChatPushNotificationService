package com.chat.pushnotification.spring;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chat.pushnotification.model.PushMessage;
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

	@Bean
	public Queue<PushMessage> getMessageQueue() {
		return new ConcurrentLinkedQueue<>();
	}

	@Bean(name = "messageQueueConsumerExecutorService")
	public ExecutorService getMessageQueueConsumerExecutorService() {
		return Executors.newFixedThreadPool(10);
	}
}
