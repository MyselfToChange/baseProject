package com.gcx.api.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class redisConfig {
	
	@Autowired
	private RedisYml redisYml;
	
    @Bean
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jpc=new JedisPoolConfig();
		jpc.setMaxIdle(redisYml.getPool().get("max-idle"));
		jpc.setMaxTotal(redisYml.getPool().get("max-active"));
		jpc.setMaxWaitMillis(redisYml.getPool().get("max-wait"));
		return jpc;
	}
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){
    	JedisConnectionFactory jcf=new JedisConnectionFactory();
    	jcf.setHostName(redisYml.getHost());
    	jcf.setPort(redisYml.getPort());
    	jcf.setPassword(redisYml.getPassword());
    	jcf.setUsePool(redisYml.isUsePool());
    	jcf.setPoolConfig(jedisPoolConfig());
    	return jcf;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(){
    	StringRedisTemplate srt=new StringRedisTemplate();
    	srt.setConnectionFactory(jedisConnectionFactory());
		return srt;
    }
}
