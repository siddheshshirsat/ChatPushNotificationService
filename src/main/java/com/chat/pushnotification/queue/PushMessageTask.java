package com.chat.pushnotification.queue;

import java.util.concurrent.Callable;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.chat.pushnotification.model.PushMessage;
import com.chat.pushnotification.state.ActiveConnections;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PushMessageTask implements Callable<Boolean> {
	private ActiveConnections activeConnections;
	private ObjectMapper objectMapper;
	private PushMessage pushMessage;

	@Override
	public Boolean call() throws Exception {
		System.out.println("Reached....pushing message " + pushMessage);
		WebSocketSession webSocketSession = activeConnections.getWebSocketSession(pushMessage.getTo());
		try {
			webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(pushMessage)));
		} catch (IllegalStateException | NullPointerException ex) {
			// the client against this webSocketSession was either not present or seems to
			// have disconnected
			activeConnections.remove(pushMessage.getTo());
			return false;
		}
		return true;
	}
}
