package com.gladguys.alucoapi.exception;

import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	private Throwable cause;
	private HttpStatus httpStatus;

	public ResponseException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public ResponseException(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public Throwable getCause() {
		return cause;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
