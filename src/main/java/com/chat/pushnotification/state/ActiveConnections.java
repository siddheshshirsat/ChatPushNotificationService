package com.chat.pushnotification.state;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ActiveConnections {
	private Map<String, WebSocketSession> activeConnections;
	private Map<String, String> sessionIdMap;
	
	@Inject
	public ActiveConnections() {
		activeConnections = new ConcurrentHashMap<>();
		sessionIdMap = new ConcurrentHashMap<>();
	}
	
	public void addConnection(String userId, WebSocketSession webSocketSession) {
		activeConnections.put(userId, webSocketSession);
		sessionIdMap.put(webSocketSession.getId(), userId);
	}

	public WebSocketSession getWebSocketSession(String userId) {
		return activeConnections.get(userId);
	}

	public void remove(WebSocketSession webSocketSession) {
		String userId = sessionIdMap.get(webSocketSession.getId());
		sessionIdMap.remove(webSocketSession.getId());
		activeConnections.remove(userId);
	}
}
