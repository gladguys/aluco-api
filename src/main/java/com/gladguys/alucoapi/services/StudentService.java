package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface StudentService {

	List<Student> findAll();
	Student getById(Long id);
	Student save(Student student);
	Set<Student> getAllByTeacher(Long teacherId);
	Student update(Student student);
	void deleteById(Long id);
	boolean existsById(Long id);

}
