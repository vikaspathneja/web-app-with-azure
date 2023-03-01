package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Appconfig {

//	@Value("${redishosturl}") 
//	private String redishosturl;
//	
//	@Value("${redishostport}")
//	private String redisPort;
	
	   @Bean 
	   public PasswordEncoder passwordEncoder() { 
	      return new BCryptPasswordEncoder(); 
	   } 
	   @Bean 
	   public PasswordEncoder passwordEncoder2() { 
	      return new BCryptPasswordEncoder(); 
	   }
	   
	   	   
//	   @Bean
//	   public LettuceConnectionFactory redisConnectionFactory() {
//		return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redishosturl, Integer.valueOf(redisPort)));
//		   
//	   }
	   
	   
	   
//	   @Bean
//	   public RedisTemplate<String, Object> redisTemplate(){
//		   RedisTemplate<String, Object> rt=new RedisTemplate<String, Object>();
//		   rt.setConnectionFactory(redisConnectionFactory());
//		   rt.setKeySerializer(new StringRedisSerializer());
//		   rt.setHashKeySerializer(new StringRedisSerializer());
//		   rt.setHashKeySerializer(new JdkSerializationRedisSerializer());
//		   rt.setValueSerializer(new JdkSerializationRedisSerializer());	
//		   rt.setEnableTransactionSupport(true);
//		   rt.afterPropertiesSet();
//		   return rt;
//		   
//	   }
}
