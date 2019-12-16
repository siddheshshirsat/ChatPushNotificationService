package com.chat.pushnotification.handler;

import java.io.IOException;
import java.util.Queue;

import javax.inject.Inject;

import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import com.chat.pushnotification.model.DeliverMessageRequest;
import com.chat.pushnotification.model.PushMessage;

@Component
public class DeliverMessageHandler {
	@Inject
	private Queue<PushMessage> messageQueue;

	public boolean deliverMessage(DeliverMessageRequest deliverMessageRequest) throws MessagingException, IOException {
		PushMessage pushMessage = new PushMessage(deliverMessageRequest.getFrom(), deliverMessageRequest.getTo(), deliverMessageRequest.getContent(), deliverMessageRequest.getTimestamp());
		System.out.println("Reached....enquing message " + pushMessage);
		messageQueue.add(pushMessage);
		return true;
	}
}
