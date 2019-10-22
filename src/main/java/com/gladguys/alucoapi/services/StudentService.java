package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Student;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface StudentService {

	Student getById(Long id);
	Student save(Student student);
	Set<Student> getAllByTeacher(Long teacherId);
	Student update(Student student);
	void deleteById(Long id);

}
