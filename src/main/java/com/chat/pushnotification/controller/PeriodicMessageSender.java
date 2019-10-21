package com.chat.pushnotification.controller;

import javax.inject.Inject;

import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PeriodicMessageSender {
	@Inject
	private SimpMessagingTemplate simpMessagingTemplate;

	@Inject
	private ObjectMapper objectMapper;

   @Scheduled(fixedRate = 1000)
   public void reportCurrentTime() throws MessagingException, JsonProcessingException {
//		ServerMessage serverMessage = new ServerMessage("server", "testUser", "message from Server");
//		simpMessagingTemplate.convertAndSendToUser("testUser", "/queue/messages",
//				objectMapper.writeValueAsString(serverMessage));
   }
}
