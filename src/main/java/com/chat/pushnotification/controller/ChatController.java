package com.chat.pushnotification.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.chat.pushnotification.state.ActiveConnections;

@Controller
public class ChatController implements ApplicationListener<ApplicationEvent> {
	@Inject
	private ActiveConnections activeConnections;
	
	@MessageMapping("/subscribe")
	public void sendSpecific(Principal user, @Header("simpSessionId") String sessionId) throws Exception {
		System.out.println("Reached.....user = " + user);
		activeConnections.addConnection(user.getName());
	}

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
//		System.out.println("Reached..." + applicationEvent.toString());
	}
}
