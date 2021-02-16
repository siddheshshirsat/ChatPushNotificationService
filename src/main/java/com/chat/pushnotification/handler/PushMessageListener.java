package com.chat.pushnotification.handler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.chat.pushnotification.model.PushMessage;
import com.chat.pushnotification.state.ActiveConnections;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PushMessageListener {
	@Inject
	private ActiveConnections activeConnections;

	@Inject
	private ObjectMapper objectMapper;

	@JmsListener(destination = "pushMessageQueue", containerFactory = "pushMessageQueueListenerContainerFactory")
	public void receivePushMessage(PushMessage pushMessage) {
		System.out.println("Reached....enquing message " + pushMessage);

		final WebSocketSession webSocketSession = activeConnections.getWebSocketSession(pushMessage.getTo());
		try {
			webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(pushMessage)));
		} catch (IllegalStateException | NullPointerException | IOException ex) {
			ex.printStackTrace();
		}
	}
}
