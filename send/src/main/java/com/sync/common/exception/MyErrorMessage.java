package com.sync.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sync.common.ErrorCode;

/**
 * 错误消息返回的json对应的Body体内容
 * 
 * @author 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyErrorMessage {

	public MyErrorMessage() {
	}

	public MyErrorMessage(int code, String errorMessage) {
		super();
		this.code = code;
		this.errorMessage = errorMessage;
	}

	public MyErrorMessage(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public MyErrorMessage(String errorType, int code, String errorMessage) {
		super();
		this.errorType = errorType;
		this.code = code;
		this.errorMessage = errorMessage;
	}

	private String errorType;

	private int code = ErrorCode.INVALID_PARAM.CODE;

	private String errorMessage = ErrorCode.INVALID_PARAM.MSG;

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "Meta{" + "errorType='" + errorType + '\'' + ", code=" + code + ", errorMessage='" + errorMessage + '\''
				+ '}';
	}

	public static final class MetaBuilder {
		private String errorType;
		private int code = 200;
		private String errorMessage;

		private MetaBuilder() {
		}

		public static MetaBuilder newBuilder() {
			return new MetaBuilder();
		}

		public MetaBuilder withErrorType(String errorType) {
			this.errorType = errorType;
			return this;
		}

		public MetaBuilder withCode(int code) {
			this.code = code;
			return this;
		}

		public MetaBuilder withErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
			return this;
		}

		public MyErrorMessage build() {
			MyErrorMessage meta = new MyErrorMessage(errorType, code, errorMessage);
			return meta;
		}
	}
}
