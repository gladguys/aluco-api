package com.gladguys.alucoapi.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ApiException {

	private final String message;
	private final int status;
	private final Date timestamp;

	public ApiException(String message, HttpStatus httpStatus, Date timestamp) {
		this.message = message;
		this.status = httpStatus.value();
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
