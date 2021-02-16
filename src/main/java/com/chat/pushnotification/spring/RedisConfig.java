package com.chat.pushnotification.spring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {
	// Setting up the Jedis connection factory.
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
		redisClusterConfiguration.addClusterNode(new RedisNode("127.0.0.1", 7000));
		redisClusterConfiguration.addClusterNode(new RedisNode("127.0.0.1", 7001));
		redisClusterConfiguration.addClusterNode(new RedisNode("127.0.0.1", 7002));
		return new JedisConnectionFactory(redisClusterConfiguration);
	}

	// Setting up the Redis template object.
	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
	}
}
