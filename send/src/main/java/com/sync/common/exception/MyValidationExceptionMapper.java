package com.sync.common.exception;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.jersey.validation.JerseyViolationException;

/**
 * 整理错误处理，处理API返回的自定义的错误接口
 * 
 * @author 
 */
@Provider
public class MyValidationExceptionMapper implements ExceptionMapper<JerseyViolationException> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyValidationExceptionMapper.class);

	/**
	 * generate the error response ,the body of the response is from the
	 * operationException
	 * 
	 * @param exception
	 *            operationException
	 * @return response
	 */
	@Override
	public Response toResponse(JerseyViolationException exception) {
		StringBuffer sb = new StringBuffer();
		Iterator<ConstraintViolation<?>> it = exception.getConstraintViolations().iterator();
		if (it.hasNext()) {
			ConstraintViolation<?> i = it.next();
			sb.append(i.getPropertyPath()).append(i.getMessage()).append(", value ").append(i.getInvalidValue());
		}
		return Response.ok().type(MediaType.APPLICATION_JSON_TYPE).entity(new MyErrorMessage(sb.toString())).build();

	}

	protected long logException(Exception exception) {
		long id = ThreadLocalRandom.current().nextLong();
		logException(id, exception);
		return id;
	}

	protected void logException(long id, Exception exception) {
		LOGGER.error(formatLogMessage(id, exception), exception);
	}

	/**
	 * format the error message
	 *
	 * @param id
	 *            the generate uuid for the log
	 * @param exception
	 *            exception
	 * @return String format error
	 */
	protected String formatLogMessage(long id, Throwable exception) {
		return String.format("Error handling a request: %016x", id);
	}

}
