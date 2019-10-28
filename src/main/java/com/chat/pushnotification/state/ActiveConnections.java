package com.chat.pushnotification.state;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class ActiveConnections {
	private Map<String, String> activeConnections;
	
	@Inject
	public ActiveConnections() {
		activeConnections = new ConcurrentHashMap<>();
	}
	
	public void addConnection(String userId) {
		activeConnections.put(userId, userId);
	}
	
	public Set<String> getAllConnections() {
		return activeConnections.keySet();
	}
}
