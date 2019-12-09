package com.chat.pushnotification.state;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ActiveConnections {
	private Map<String, WebSocketSession> activeConnections;
	
	@Inject
	public ActiveConnections() {
		activeConnections = new ConcurrentHashMap<>();
	}
	
	public void addConnection(String userId, WebSocketSession webSocketSession) {
		activeConnections.put(userId, webSocketSession);
	}

	public WebSocketSession getWebSocketSession(String userId) {
		return activeConnections.get(userId);
	}

	public Set<String> getAllConnections() {
		return activeConnections.keySet();
	}

	public void remove(String userId) {
		activeConnections.remove(userId);
	}
}
