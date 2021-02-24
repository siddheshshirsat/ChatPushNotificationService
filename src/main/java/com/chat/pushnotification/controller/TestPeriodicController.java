package com.chat.pushnotification.controller;

import javax.inject.Inject;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestPeriodicController {
	@Inject
	private DeliverMessageController deliverMessageController;

	@Inject
    RedisTemplate<String, String> redisTemplate;

//	@Scheduled(fixedRate = 1000)
	public void test() {
		try {
			System.out.println("Reached....1");

//			DeliverMessageRequest deliverMessageRequest = new DeliverMessageRequest();
//			deliverMessageRequest.setContent("test Content");
//			deliverMessageRequest.setFrom("testUserId2");
//			deliverMessageRequest.setTo("testUserId1");
//			deliverMessageRequest.setTimestamp(System.currentTimeMillis());
//			DeliverMessageResponse deliverMessageResponse = deliverMessageController.deliverMessage(deliverMessageRequest);
//			System.out.println("Reached...periodic deliver message " + deliverMessageResponse);
			
//			HashOperations<String, String, String> hashOperations = redisTemplate.<String, String>opsForHash();
//			long timestamp = System.currentTimeMillis();
//			hashOperations.put("testCache", "testKey" + timestamp, "testValue" + timestamp);
//			System.out.println("Reached....cache = " + hashOperations.entries("testCache"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
