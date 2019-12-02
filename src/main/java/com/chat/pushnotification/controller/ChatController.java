package com.chat.pushnotification.controller;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController implements ApplicationListener<ApplicationEvent> {
	@Override
	public void onApplicationEvent(ApplicationEvent applicationEvent) {
//		System.out.println("Reached..." + applicationEvent.toString());
	}
}
