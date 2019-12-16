package com.chat.pushnotification.queue;

import java.util.Queue;
import java.util.concurrent.ExecutorService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;

import com.chat.pushnotification.model.PushMessage;
import com.chat.pushnotification.state.ActiveConnections;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named
public class MessageQueueConsumerTask implements Runnable {
	@Inject
	private Queue<PushMessage> messageQueue;

	@Inject
	@Qualifier("messageQueueConsumerExecutorService")
	private ExecutorService messageQueueConsumerExecutorService;

	@Inject
	private ActiveConnections activeConnections;

	@Inject
	private ObjectMapper objectMapper;

	@PostConstruct
	public void startMessageQueueConsumer() {
		messageQueueConsumerExecutorService.execute(this);
	}

	@Override
	public void run() {
		while (true) {
			PushMessage pushMessage = messageQueue.poll();

			if (pushMessage != null) {
				messageQueueConsumerExecutorService
						.submit(new PushMessageTask(activeConnections, objectMapper, pushMessage));
			}
		}
	}
}
