package com.chat.pushnotification.handler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.chat.pushnotification.model.DeliverMessageRequest;
import com.chat.pushnotification.model.PushMessage;
import com.chat.pushnotification.state.ActiveConnections;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DeliverMessageHandler {
	@Inject
	private ActiveConnections activeConnections;

	@Inject
	private ObjectMapper objectMapper;

	public boolean deliverMessage(DeliverMessageRequest deliverMessageRequest) throws MessagingException, IOException {
		PushMessage pushMessage = new PushMessage(deliverMessageRequest.getFrom(), deliverMessageRequest.getTo(), deliverMessageRequest.getContent(), deliverMessageRequest.getTimestamp());
		System.out.println("Reached....pushing message " + pushMessage);

		WebSocketSession webSocketSession = activeConnections.getWebSocketSession(deliverMessageRequest.getTo());
		webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(pushMessage)));
		return true;
	}
}
