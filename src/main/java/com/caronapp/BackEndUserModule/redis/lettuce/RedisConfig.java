package com.caronapp.BackEndUserModule.redis.lettuce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.caronapp.BackEndUserModule.redis.lettuce.entity.RedisDataObject;

@Configuration
public class RedisConfig {
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory("localhost", 6379);
	}

	@Bean(name = "reactiveRedisTemplateString")
	public ReactiveRedisTemplate<String, RedisDataObject> reactiveRedisTemplateString
	  (ReactiveRedisConnectionFactory connectionFactory) {
		
		StringRedisSerializer keySerializer = new StringRedisSerializer();
	    Jackson2JsonRedisSerializer<RedisDataObject> valueSerializer =
	      new Jackson2JsonRedisSerializer<>(RedisDataObject.class);
	    RedisSerializationContext.RedisSerializationContextBuilder<String, RedisDataObject> builder =
	      RedisSerializationContext.newSerializationContext(keySerializer);
	    RedisSerializationContext<String, RedisDataObject> context = 
	      builder.value(valueSerializer).build();
	    ReactiveRedisTemplate<String, RedisDataObject> reactiveRedisTemplateString = new ReactiveRedisTemplate<String, RedisDataObject>(connectionFactory, context);
	 
	    return reactiveRedisTemplateString;
	}

}
