package com.chat.pushnotification.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.pushnotification.handler.DeliverMessageHandler;
import com.chat.pushnotification.model.DeliverMessageRequest;
import com.chat.pushnotification.model.DeliverMessageResponse;

@RestController
public class DeliverMessageController {
	@Inject
	private DeliverMessageHandler deliverMessageHandler;

	@RequestMapping("/deliverMessage")
	public @ResponseBody DeliverMessageResponse deliverMessage(@RequestBody DeliverMessageRequest deliverMessageRequest) {
		boolean isDelivered = false;
		try {
			isDelivered = deliverMessageHandler.deliverMessage(deliverMessageRequest);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new DeliverMessageResponse(isDelivered);
	}
}
