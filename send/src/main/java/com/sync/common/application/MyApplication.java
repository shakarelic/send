package com.sync.common.application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sync.common.application.spring.SpringBundle;
import com.sync.common.exception.MyApplicationExceptionMapper;
import com.sync.common.exception.MyValidationExceptionMapper;
import com.sync.common.filter.MyThreadNameFilter;
import com.sync.common.filter.TimedRequestFilter;
import com.sync.common.filter.TimedResponseFilter;

import io.dropwizard.Application;
import io.dropwizard.Bundle;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public abstract class MyApplication<T extends BaseConfiguration> extends Application<T> {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected BaseConfiguration myConfiguration;

	public static MetricRegistry metricRegistry;

	@Override
	public String getName() {
		if (myConfiguration == null || myConfiguration.getServiceName() == null) {
			return super.getName();
		}
		return myConfiguration.getServiceName();
	}

	@Override
	public void initialize(Bootstrap bootstrap) {
		MyApplication.metricRegistry = bootstrap.getMetricRegistry();
		bootstrap.addBundle(createSwaggerBundle()); // add Swagger
		bootstrap.addBundle(createSpringBundle()); // add Spring
		initializeCustom(bootstrap);
		bootstrap.addBundle(new AssetsBundle("/favicon.ico"));
		bootstrap.addBundle(new AssetsBundle());
		// add dropwizard-forms
		bootstrap.addBundle(new MultiPartBundle());
	}

	protected void initializeCustom(Bootstrap bootstrap) {

	}

	/**
	 * When the application runs, this is called after the {@link Bundle}s are
	 * run. Override it to add providers, resources, etc. for your application.
	 *
	 * @param configuration
	 *            the parsed {@link Configuration} object
	 * @param environment
	 *            the application's {@link Environment}
	 * @throws Exception
	 *             if something goes wrong
	 */
	@Override
	public void run(T configuration, Environment environment) throws Exception {
		logger.info("begin to load the system.");
		this.myConfiguration = configuration;
		// 异常拦截
		environment.jersey().register(new MyApplicationExceptionMapper());
		// Validation 错误拦截 如notNull
		environment.jersey().register(new MyValidationExceptionMapper());
		// 支持form-data
		environment.jersey().register(MultiPartFeature.class);
		runCustom(configuration, environment);
		logger.info("load the system success.");
	}

	protected void runCustom(T configuration, Environment environment) {
		environment.jersey().register(new MyThreadNameFilter());
		environment.jersey().register(new TimedRequestFilter(configuration));
		environment.jersey().register(new TimedResponseFilter(configuration));
		configObjectMapper(environment);
	}
	
	/**
     * 配置jackson
     *
     * @param environment
     */
    protected void configObjectMapper(Environment environment) {
        ObjectMapper objectMapper = environment.getObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
    }


	private SwaggerBundle<T> createSwaggerBundle() {
		return new SwaggerBundle<T>() {
			@Override
			protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(T configuration) {
				// this would be the preferred way to set up swagger, you can
				// also construct the object here programtically if you want
				return configuration.swaggerBundleConfiguration;
			}
		};
	}

	/**
	 * 这里最好对refresh提供false参数, 不然获取不到dw实例
	 *
	 * @return
	 */
	private SpringBundle<T> createSpringBundle() {
		return new SpringBundle<T>(
				new ClassPathXmlApplicationContext(new String[] { getApplicationPath() }, false, null), true, true,
				true);
	}

	protected String getApplicationPath() {
		return "applicationContext.xml";
	}
}
