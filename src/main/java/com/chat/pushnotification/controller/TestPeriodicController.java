package com.chat.pushnotification.controller;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chat.pushnotification.model.DeliverMessageRequest;
import com.chat.pushnotification.model.DeliverMessageResponse;

@Component
public class TestPeriodicController {
	@Inject
	private DeliverMessageController deliverMessageController;

//	@Scheduled(fixedRate = 1000)
	public void test() {
		try {
			DeliverMessageRequest deliverMessageRequest = new DeliverMessageRequest();
			deliverMessageRequest.setContent("test Content");
			deliverMessageRequest.setFrom("testUserId2");
			deliverMessageRequest.setTo("testUserId1");
			deliverMessageRequest.setTimestamp(System.currentTimeMillis());
			DeliverMessageResponse deliverMessageResponse = deliverMessageController.deliverMessage(deliverMessageRequest);
			System.out.println("Reached...periodic deliver message " + deliverMessageResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
