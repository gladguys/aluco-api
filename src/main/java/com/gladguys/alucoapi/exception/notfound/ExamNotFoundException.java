package com.gladguys.alucoapi.exception.notfound;

public class ExamNotFoundException extends RuntimeException{

	public ExamNotFoundException(Long id) {
		super("Nenhuma prova encontrada - id: " + id);
	}
}
