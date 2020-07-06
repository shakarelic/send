package com.sync.common.exception;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.sync.common.ErrorCode;

/**
 * 错误处理类
 * 
 */
public class MyApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the internal error code
	 */
	public int code;

	/**
	 * error message
	 */
	public String message;

	/**
	 * http Status
	 */
	public Response.Status status;

	/**
	 * the DefaultMessage
	 */
	public MyApplicationException() {
		this((Response.Status) null, (Throwable) null);
	}

	public MyApplicationException(int code, String message) {
		this(code, null, message, (Throwable) null);
	}

	/**
	 *
	 * @param status
	 *            the status
	 */
	public MyApplicationException(int code, Response.Status status, String message) {
		this(code, status, message, (Throwable) null);
	}

	/**
	 * 最终构造
	 * 
	 * @param code
	 * @param status
	 * @param message
	 * @param cause
	 */
	public MyApplicationException(int code, Response.Status status, String message, Throwable cause) {
		super(message, cause);
		if (StringUtils.isEmpty(message)) {
			this.message = ErrorCode.INTERNAL_ERROR.MSG;
		} else {
			this.message = message;
		}
		if (code <= 0) {
			this.code = ErrorCode.INTERNAL_ERROR.CODE;
		} else {
			this.code = code;
		}
		if (status == null) {
			this.status = Response.Status.OK;
		} else {
			this.status = status;
		}

	}

	/**
	 * @param code
	 *            the code of the error
	 * @param message
	 *            the message of the error
	 * @param throwable
	 *            cause of error
	 */
	public MyApplicationException(int code, String message, Throwable throwable) {
		this(code, (Response.Status) null, message, throwable);
	}

	public MyApplicationException(Response.Status status) {
		this(0, status, (String) null, (Throwable) null);
	}

	public MyApplicationException(Response.Status status, String message) {
		this(0, status, message, (Throwable) null);
	}

	public MyApplicationException(Response.Status status, Throwable cause) throws IllegalArgumentException {
		this(0, status, (String) null, (Throwable) null);
	}

	/**
	 * @param message
	 *            the default message
	 */
	public MyApplicationException(String message) {
		this(0, (Response.Status) null, message, (Throwable) null);
	}

	/**
	 * @param throwable
	 *            cause of error
	 * @param message
	 *            error message
	 */
	public MyApplicationException(String message, Throwable throwable) {
		this(0, (Response.Status) null, message, throwable);
	}

	public MyApplicationException(Throwable cause) {
		this((Response.Status) null, cause);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Response.Status getStatus() {
		return status;
	}

	public void setStatus(Response.Status status) {
		this.status = status;
	}

}
