package com.gladguys.alucoapi.exception;

public class ApiResponseException extends RuntimeException {

	public ApiResponseException(String message) {
		super(message);
	}

	public ApiResponseException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
