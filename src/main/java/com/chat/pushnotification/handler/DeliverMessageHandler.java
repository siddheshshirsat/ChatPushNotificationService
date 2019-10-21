package com.chat.pushnotification.handler;

import javax.inject.Inject;

import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.chat.pushnotification.model.ServerMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeliverMessageHandler {
	@Inject
	private SimpMessagingTemplate simpMessagingTemplate;

	@Inject
	private ObjectMapper objectMapper;

//	@Inject
//	private ConcurrentHashMap<String, ServerDetails> activeConnections;

	public boolean deliverMessage(String recipientId, String messageContent) throws MessagingException, JsonProcessingException {
		ServerMessage serverMessage = new ServerMessage("server", "testUser", "message from Server");
		simpMessagingTemplate.convertAndSendToUser(recipientId, "/queue/messages", objectMapper.writeValueAsString(serverMessage));
		return true;
	}
}
