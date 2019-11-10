package com.gladguys.alucoapi.exception.notfound;

public class ClassNotFoundException extends RuntimeException{

	public ClassNotFoundException(Long id) {
		super("Nenhuma turma encontrada - id: " + id);
	}
}
