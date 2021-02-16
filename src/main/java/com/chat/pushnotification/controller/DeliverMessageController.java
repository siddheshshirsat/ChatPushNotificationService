package com.chat.pushnotification.controller;

import javax.inject.Inject;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.pushnotification.model.DeliverMessageRequest;
import com.chat.pushnotification.model.DeliverMessageResponse;
import com.chat.pushnotification.model.PushMessage;

@RestController
public class DeliverMessageController {
	@Inject
	private JmsTemplate jmsTemplate;

	@RequestMapping("/deliverMessage")
	public @ResponseBody DeliverMessageResponse deliverMessage(
			@RequestBody DeliverMessageRequest deliverMessageRequest) {

		final PushMessage pushMessage = new PushMessage(deliverMessageRequest.getFrom(), deliverMessageRequest.getTo(),
				deliverMessageRequest.getContent(), deliverMessageRequest.getTimestamp());
		jmsTemplate.convertAndSend("pushMessageQueue", pushMessage);
		return new DeliverMessageResponse(true);
	}
}
