package com.gladguys.alucoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Date;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {ApiResponseException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiResponseException e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException exception = new ApiException(e.getMessage(), badRequest, new Date());

		return new ResponseEntity<>(exception, badRequest);
	}

	@ExceptionHandler(value = {SQLException.class})
	public ResponseEntity<Object> handleSQLException(Exception e) {
		HttpStatus internalServer = HttpStatus.INTERNAL_SERVER_ERROR;
		ApiException exception = new ApiException(e.getMessage(), internalServer, new Date());

		return new ResponseEntity<>(exception, internalServer);
	}
}
