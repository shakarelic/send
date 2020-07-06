package com.sync.common.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sync.common.application.BaseConfiguration;

/**
 * Created by IntelliJ IDEA.
 *
 * @author
 * @date 16-9-12 下午1:46.
 */
@Priority(Priorities.HEADER_DECORATOR + 10)
@Provider
public class TimedResponseFilter implements ContainerResponseFilter {

	private static final Logger logger = LoggerFactory.getLogger("");

	private boolean debug = true;

	public TimedResponseFilter() {

	}

	public TimedResponseFilter(BaseConfiguration configuration) {
		this.debug = configuration.isDebug();
	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		Object startTimeObj = requestContext.getProperty("start_time");
		if (startTimeObj != null) {
			Long startTime = (Long) startTimeObj;
			logger.info("{} {} elapsed {} millisecond path {}，query{}，reponse {}", requestContext.getMethod(),
					requestContext.getUriInfo().getAbsolutePath().getPath(), System.currentTimeMillis() - startTime,
					requestContext.getUriInfo().getPathParameters(), requestContext.getUriInfo().getQueryParameters(), responseContext.getEntity());
		}
	}
}
