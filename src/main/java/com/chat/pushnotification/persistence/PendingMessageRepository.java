package com.chat.pushnotification.persistence;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.repository.Repository;

import com.chat.pushnotification.model.PushMessage;
import com.google.common.collect.ImmutableList;

@Named
public class PendingMessageRepository implements Repository<PushMessage, String> {
	private Map<String, Queue<PushMessage>> pendingMessageRepository;

	@Inject
	public PendingMessageRepository() {
		this.pendingMessageRepository = new ConcurrentHashMap<>();
	}

	public void save(PushMessage pushMessage) {
		Queue<PushMessage> queue = pendingMessageRepository.get(pushMessage.getTo());
		if (queue == null) {
			pendingMessageRepository.put(pushMessage.getTo(), new LinkedList<>());
		}
		pendingMessageRepository.get(pushMessage.getTo()).add(pushMessage);
	}

	public List<PushMessage> get(String userId) {
		Queue<PushMessage> pendingMessageQueue = pendingMessageRepository.get(userId);
		return pendingMessageQueue == null || pendingMessageQueue.isEmpty()
				? ImmutableList.<PushMessage>builder().build()
				: ImmutableList.<PushMessage>builder().addAll(pendingMessageRepository.get(userId)).build();
	}

	public void clear(String userId) {
		Queue<PushMessage> queue = pendingMessageRepository.get(userId);
		if (queue != null) {
			queue.clear();
		}
	}
}
