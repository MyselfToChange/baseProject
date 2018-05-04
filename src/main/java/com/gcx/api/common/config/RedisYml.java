package com.gcx.api.common.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.redis")
public class RedisYml {
	
     private String host;
     private Integer port;
     private String password;
     private Integer timeout;
     private boolean usePool;
     private Map<String,Integer> pool;
     
     
	/**
	 * @return the timeout
	 */
	public Integer getTimeout() {
		return timeout;
	}
	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	/**
	 * @return the usePool
	 */
	public boolean isUsePool() {
		return usePool;
	}
	/**
	 * @param usePool the usePool to set
	 */
	public void setUsePool(boolean usePool) {
		this.usePool = usePool;
	}
	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}
	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}
	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the pool
	 */
	public Map<String, Integer> getPool() {
		return pool;
	}
	/**
	 * @param pool the pool to set
	 */
	public void setPool(Map<String, Integer> pool) {
		this.pool = pool;
	}
     
     
}
