package com.gladguys.alucoapi.exception.notfound;

public class CallNotFoundException extends RuntimeException {

	public CallNotFoundException(Long id) {
		super("Nenhuma chamada encontrada - id: " + id);
	}
}
