package com.chat.pushnotification.handler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.chat.pushnotification.model.PushMessage;
import com.chat.pushnotification.persistence.PendingMessageRepository;
import com.chat.pushnotification.state.ActiveConnections;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PushMessageListener {
	@Inject
	private ActiveConnections activeConnections;

	@Inject
	private ObjectMapper objectMapper;

	@Inject
	private PendingMessageRepository pendingMessageRepository;

	@JmsListener(destination = "pushMessageQueue", containerFactory = "pushMessageQueueListenerContainerFactory")
	public void receivePushMessage(PushMessage pushMessage) {
		final WebSocketSession webSocketSession = activeConnections.getWebSocketSession(pushMessage.getTo());
		if(webSocketSession != null) {
			try {
				webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(pushMessage)));
				System.out.println("Reached....pushed message " + pushMessage);
			} catch (IllegalStateException | NullPointerException | IOException ex) {
				ex.printStackTrace();
			}
		} else {
			pendingMessageRepository.save(pushMessage);
			System.out.println("Reached....saved message " + pushMessage);
		}
	}
}
