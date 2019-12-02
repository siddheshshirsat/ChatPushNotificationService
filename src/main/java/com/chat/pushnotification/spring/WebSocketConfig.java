package com.chat.pushnotification.spring;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.chat.pushnotification.handler.SocketTextHandler;
import com.chat.pushnotification.state.ActiveConnections;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	@Inject
	private ActiveConnections activeConnections;
	
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(new SocketTextHandler(activeConnections), "/user");
	}
	
	@Bean
	public TaskScheduler taskScheduler() {
	    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
	    taskScheduler.setPoolSize(10);
	    taskScheduler.initialize();
	    return taskScheduler;
	}
}
