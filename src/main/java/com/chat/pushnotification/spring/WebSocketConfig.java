package com.chat.pushnotification.spring;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.chat.pushnotification.handler.PushMessageListener;
import com.chat.pushnotification.handler.SocketTextHandler;
import com.chat.pushnotification.persistence.PendingMessageRepository;
import com.chat.pushnotification.state.ActiveConnections;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Inject
	private ActiveConnections activeConnections;

	@Inject
	private PendingMessageRepository pendingMessageRepository;

	@Inject
	private PushMessageListener pushMessageListener;

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new SocketTextHandler(activeConnections, pendingMessageRepository, pushMessageListener),
				"/user");
	}

	@Bean
	public TaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(10);
		taskScheduler.initialize();
		return taskScheduler;
	}
}
