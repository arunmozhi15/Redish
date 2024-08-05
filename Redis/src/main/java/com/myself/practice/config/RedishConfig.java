package com.myself.practice.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.myself.practice.entity.StudentEntity;
@EnableCaching
@Configuration
public class RedishConfig {
	@Configuration
	public class RedisConfig {

//	    @Bean
//	    public RedisTemplate<String, StudentEntity> redisTemplate(RedisConnectionFactory connectionFactory) {
//	        RedisTemplate<String, StudentEntity> template = new RedisTemplate<>();
//	        template.setConnectionFactory(connectionFactory);
//	        template.setKeySerializer(new StringRedisSerializer());
//	        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(StudentEntity.class));
//	        return template;
//	    }
	}

}
