package com.chat.pushnotification.handler;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.chat.pushnotification.model.PushMessage;
import com.chat.pushnotification.persistence.PendingMessageRepository;
import com.chat.pushnotification.state.ActiveConnections;

@Named
public class SocketTextHandler extends TextWebSocketHandler {
	private ActiveConnections activeConnections;
	private PendingMessageRepository pendingMessageRepository;
	private PushMessageListener pushMessageListener;

	@Inject
	public SocketTextHandler(ActiveConnections activeConnections, PendingMessageRepository pendingMessageRepository,
			PushMessageListener pushMessageListener) {
		super();
		this.activeConnections = activeConnections;
		this.pendingMessageRepository = pendingMessageRepository;
		this.pushMessageListener = pushMessageListener;
	}

	@Override
	public void handleTextMessage(WebSocketSession webSocketSession, TextMessage message)
			throws InterruptedException, IOException {
		String payload = message.getPayload();
		System.out.println("Reached..." + activeConnections);
		if (payload.startsWith("Connect:")) {
			String userId = payload.substring("Connect:".length());
			activeConnections.addConnection(userId, webSocketSession);

			List<PushMessage> pendingMessages = pendingMessageRepository.get(userId);
			pendingMessages.stream().forEach(pendingMessage -> pushMessageListener.receivePushMessage(pendingMessage));
			pendingMessageRepository.clear(userId);
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		activeConnections.remove(session);
		System.out.println("Reached...connection closed ");
	}
}
