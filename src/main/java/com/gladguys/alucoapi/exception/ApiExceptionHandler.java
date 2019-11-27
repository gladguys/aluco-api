package com.gladguys.alucoapi.exception;

import com.gladguys.alucoapi.exception.notfound.CallNotFoundException;
import com.gladguys.alucoapi.exception.notfound.ClassNotFoundException;
import com.gladguys.alucoapi.exception.notfound.ExamNotFoundException;
import com.gladguys.alucoapi.exception.notfound.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;
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

	@ExceptionHandler(value = {ValidationException.class})
	public ResponseEntity<Object> handleValidationException(Exception e) {
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException exception = new ApiException(e.getMessage(), badRequest, new Date());

		return new ResponseEntity<>(exception, badRequest);
	}

	@ExceptionHandler(value = {ClassNotFoundException.class})
	public ResponseEntity<Object> handleClassNotFoundException(Exception e) {
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		ApiException exception = new ApiException(e.getMessage(), notFound, new Date());

		return new ResponseEntity<>(exception, notFound);
	}

	@ExceptionHandler(value = {StudentNotFoundException.class})
	public ResponseEntity<Object> handleStudentNotFoundException(Exception e) {
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		ApiException exception = new ApiException(e.getMessage(), notFound, new Date());

		return new ResponseEntity<>(exception, notFound);
	}

	@ExceptionHandler(value = {ExamNotFoundException.class})
	public ResponseEntity<Object> handleExamNotFoundException(Exception e) {
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		ApiException exception = new ApiException(e.getMessage(), notFound, new Date());

		return new ResponseEntity<>(exception, notFound);
	}

	@ExceptionHandler(value = {CallNotFoundException.class})
	public ResponseEntity<Object> handleCallNotFoundException(Exception e) {
		HttpStatus notFound = HttpStatus.NOT_FOUND;
		ApiException exception = new ApiException(e.getMessage(), notFound, new Date());

		return new ResponseEntity<>(exception, notFound);
	}

}
