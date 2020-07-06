package com.sync.common.filter;

import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @author:32
 * @date 2016/9/21.
 */

/**
 * A servlet filter which adds the request method and URI to the thread name processing the request
 * for the duration of the request.
 */
@Provider
public class MyThreadNameFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        ContainerRequest req = (ContainerRequest) containerRequestContext.getRequest();
        final Thread current = Thread.currentThread();
        final String oldName = current.getName();
        try {
            current.setName(formatName(req, current.getId()));
        } catch (Exception e) {
            current.setName(oldName);
        }
    }

    private static String formatName(ContainerRequest req, long id) {
        return req.getRequestUri().getPath() + "-" + String.valueOf(id);
    }
}
