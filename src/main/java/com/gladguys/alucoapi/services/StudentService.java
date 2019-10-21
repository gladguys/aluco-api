package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Student;

public interface StudentService {

	Student getById(Long id);
	Student update(Student student);
	void deleteById(Long id);

}
