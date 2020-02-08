package com.gladguys.alucoapi.exception.notfound;

public class StudentClassObservationNotFoundException extends RuntimeException {

	public StudentClassObservationNotFoundException(Long id) {
		super("Nenhuma observação sobre estudante encontrada - id: " + id);
	}
}
