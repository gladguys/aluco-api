package com.gladguys.alucoapi.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

	@Bean
	public ErrorAttributes errorAttributes() {
		// Hide exception field in the return object
		return new DefaultErrorAttributes() {
			public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
				Map<String, Object> errorAttributes = super.getErrorAttributes((WebRequest) requestAttributes, includeStackTrace);
				errorAttributes.remove("exception");
				return errorAttributes;
			}
		};
	}

	@ExceptionHandler(ResponseException.class)
	public void handleCustomException(HttpServletResponse res, ResponseException ex) throws IOException {
		res.sendError(ex.getHttpStatus().value(), ex.getMessage());
	}

	@ExceptionHandler(AuthenticationException.class)
	public void handleAccessDeniedException(HttpServletResponse res) throws IOException {
		res.sendError(HttpStatus.UNAUTHORIZED.value(), "Acesso negado");
	}

	@ExceptionHandler(Exception.class)
	public void handleException(HttpServletResponse res) throws IOException {
		res.sendError(HttpStatus.BAD_REQUEST.value(), "Erro inesperado ao processar a requisição");
	}
}
