package com.chat.pushnotification.handler;

import javax.inject.Inject;

import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.chat.pushnotification.model.DeliverMessageRequest;
import com.chat.pushnotification.model.PushMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DeliverMessageHandler {
	@Inject
	private SimpMessagingTemplate simpMessagingTemplate;

	@Inject
	private ObjectMapper objectMapper;

	public boolean deliverMessage(DeliverMessageRequest deliverMessageRequest) throws MessagingException, JsonProcessingException {
		PushMessage pushMessage = new PushMessage(deliverMessageRequest.getFrom(), deliverMessageRequest.getTo(), deliverMessageRequest.getContent(), deliverMessageRequest.getTimestamp());
		System.out.println("Reached....pushing message " + pushMessage);

		simpMessagingTemplate.convertAndSendToUser(deliverMessageRequest.getTo(), "/queue/messages", objectMapper.writeValueAsString(pushMessage));
		return true;
	}
}
