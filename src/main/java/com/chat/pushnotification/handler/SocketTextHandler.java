package com.chat.pushnotification.handler;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.chat.pushnotification.state.ActiveConnections;

@Component
public class SocketTextHandler extends TextWebSocketHandler {
	private ActiveConnections activeConnections;

	@Inject
	public SocketTextHandler(ActiveConnections activeConnections) {
		super();
		this.activeConnections = activeConnections;
	}

	@Override
	public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message)
			throws InterruptedException, IOException {
		String payload = message.getPayload();
		System.out.println("Reached..." + activeConnections);
		if(payload.startsWith("Connect:")) {
			activeConnections.addConnection(payload.substring("Connect:".length()), webSocketSession);
		}
	}
}
