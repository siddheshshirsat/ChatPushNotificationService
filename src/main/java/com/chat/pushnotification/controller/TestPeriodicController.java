package com.chat.pushnotification.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.chat.pushnotification.handler.DeliverMessageHandler;
import com.chat.pushnotification.model.DeliverMessageRequest;

@Component
public class TestPeriodicController {
	@Inject
	private DeliverMessageHandler deliverMessageHandler;

//	@Scheduled(fixedRate = 1000)
	public void test() {
		try {
			DeliverMessageRequest deliverMessageRequest = new DeliverMessageRequest();
			deliverMessageRequest.setContent("test Content");
			deliverMessageRequest.setFrom("testUserId2");
			deliverMessageRequest.setTo("testUserId1");
			deliverMessageRequest.setTimestamp(System.currentTimeMillis());
			boolean deliverMessageStatus = deliverMessageHandler.deliverMessage(deliverMessageRequest);
			System.out.println("Reached...periodic deliver message " + deliverMessageStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
