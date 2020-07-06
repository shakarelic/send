package com.sync.common.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class BaseConfiguration extends Configuration {

	@JsonProperty("swagger")
	public SwaggerBundleConfiguration swaggerBundleConfiguration;

	@JsonProperty("serviceName")
	private String serviceName;

	@JsonProperty("jdbc.url")
	private String jdbcUrl;
	@JsonProperty("jdbc.username")
	private String jdbcUsername;
	@JsonProperty("jdbc.password")
	private String jdbcPassword;
	@JsonProperty("jdbc.driver")
	private String jdbcDriver;
	@JsonProperty("jdbc2.url")
	private String jdbc2Url;
	@JsonProperty("jdbc2.username")
	private String jdbc2Username;
	@JsonProperty("jdbc2.password")
	private String jdbc2Password;
	@JsonProperty("jdbc2.driver")
	private String jdbc2Driver;
	@JsonProperty("jdbc.initialSize")
	private String jdbcInitialSize;
	@JsonProperty("jdbc.maxActive")
	private String jdbcMaxActive;
	@JsonProperty("jdbc.maxIdle")
	private String jdbcMaxIdle;

	@JsonProperty("redis.host")
	private String redisHost;
	@JsonProperty("redis.port")
	private String redisPort;
	@JsonProperty("redis.timeout")
	private String redisTimeout;
	@JsonProperty("redis.unit")
	private String redisUnit;
	@JsonProperty(value = "redis.auth", defaultValue = "")
	private String redisAuth;

	@JsonProperty("thread.pool.core.pool.size")
	private String threadPoolCorePoolSize;
	@JsonProperty("thread.pool.max.pool.size")
	private String threadPoolMaxPoolSize;
	@JsonProperty("thread.pool.queue.capacity")
	private String threadPoolQueueCapacity;
	@JsonProperty("thread.pool.keep.alive.seconds")
	private String threadPoolKeepAliveSeconds;

	@JsonProperty(value = "thread.scheduler.pool.size", defaultValue = "10")
	private String threadSchedulerPoolSize;

	@JsonProperty("http.ning.connect.timeout")
	private String httpNingConnectTimeout;
	@JsonProperty("http.ning.max.connections")
	private String httpNingMaxConnections;
	@JsonProperty("http.ning.max.connections.per.host")
	private String httpNingMaxConnectionsPerHost;
	@JsonProperty("http.ning.request.timeout")
	private String httpNingRequestTimeout;
	@JsonProperty("http.ning.read.timeout")
	private String httpNingReadTimeout;

	@JsonProperty(value = "message.send.url", defaultValue = "http://b2c.ec.adc.com/front")
	private String messageSendUrl;

	@JsonProperty(value = "debug", defaultValue = "false")
	private boolean debug;

	public String getServiceName() {
		return serviceName;
	}

	public boolean isDebug() {
		return debug;
	}

	public String getMessageSendUrl() {
		return messageSendUrl;
	}



}
