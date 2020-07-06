package com.sync.common.exception;

import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sync.common.ErrorCode;

/**
 * 整理错误处理，处理API返回的自定义的错误接口
 * 
 * @author 
 */
@Provider
public class MyApplicationExceptionMapper implements ExceptionMapper<Exception> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyApplicationExceptionMapper.class);

	/**
	 * generate the error response ,the body of the response is from the
	 * operationException
	 * 
	 * @param exception
	 *            operationException
	 * @return response
	 */
	@Override
	public Response toResponse(Exception exception) {
		// 用户自定义的运行时异常处理
		if (exception instanceof MyApplicationException) {
			// 获取用户抛出的异常信息
			int code = ((MyApplicationException) exception).getCode();
			Status status = ((MyApplicationException) exception).getStatus();
			// 记录
			// logException(exception);
			String message = exception.getMessage();
			return Response.status(status).type(MediaType.APPLICATION_JSON_TYPE)
					.entity(new MyErrorMessage(code, message)).build();
		} else if (exception instanceof WebApplicationException) {
			// 记录
			int code = ((WebApplicationException) exception).getResponse().getStatus();
			if (code == Response.Status.NOT_FOUND.getStatusCode()) {
				return Response.status(code).build();
			}
			logException(exception);
			if (code == Response.Status.FORBIDDEN.getStatusCode()) {
				MyErrorMessage error = new MyErrorMessage(Response.Status.FORBIDDEN.getStatusCode(),
						Response.Status.FORBIDDEN.name());
				return Response.ok().type(MediaType.APPLICATION_JSON_TYPE).entity(error).build();
			} else
				return Response.status(code).build();
		}
		// 其他异常
		else {
			// 记录
			logException(exception);
			MyErrorMessage error = new MyErrorMessage(ErrorCode.INTERNAL_ERROR.CODE, ErrorCode.INTERNAL_ERROR.MSG);
			return Response.ok().type(MediaType.APPLICATION_JSON_TYPE).entity(error).build();

		}
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
