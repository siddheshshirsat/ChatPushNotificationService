package com.chat.pushnotification.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.chat.pushnotification.state.ActiveConnections;

@Controller
public class ChatController implements ApplicationListener<ApplicationEvent> {
	@Inject
	private ActiveConnections activeConnections;
	
	@MessageMapping("/subscribe")
	public void subscribe(Principal user) throws Exception {
	}

	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
		if(applicationEvent.getClass().equals(SessionSubscribeEvent.class)) {
			SessionSubscribeEvent sessionSubscribeEvent = (SessionSubscribeEvent)applicationEvent;
			Principal user = sessionSubscribeEvent.getUser();
			activeConnections.addConnection(user.getName());
		}
//		System.out.println("Reached..." + applicationEvent.toString());
	}
}
