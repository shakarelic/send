package com.sync.common.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sync.common.application.BaseConfiguration;

/**
 * import javax.ws.rs.Priorities; import
 * javax.ws.rs.container.ContainerRequestContext; import
 * javax.ws.rs.container.ContainerRequestFilter; import
 * javax.ws.rs.ext.Provider;
 * 
 * import com.sync.common.applic
 * 
 * import com.sync.common.application.MyConfiguration; * Created by IntelliJ
 * IDEA.
 *
 * @author
 * @date 16-9-12 下午1:46.
 */
@Priority(Priorities.HEADER_DECORATOR + 10)
@Provider
public class TimedRequestFilter implements ContainerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(TimedResponseFilter.class);

	private boolean debug = true;

	public TimedRequestFilter() {

	}

	public TimedRequestFilter(BaseConfiguration configuration) {
		this.debug = configuration.isDebug();
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		requestContext.setProperty("start_time", System.currentTimeMillis());
		logger.debug("{} {} elapsed path {}，query{}", requestContext.getMethod(), requestContext.getUriInfo().getAbsolutePath().getPath(),
				requestContext.getUriInfo().getPathParameters(), requestContext.getUriInfo().getQueryParameters());
	}
}
