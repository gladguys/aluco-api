package com.gladguys.alucoapi.exception.notfound;

public class StudentNotFoundException extends RuntimeException {

	public StudentNotFoundException(Long id) {
		super("Nenhum aluno encontrado - id: " + id);
	}
}
