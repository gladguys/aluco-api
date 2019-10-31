package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface StudentService {

	List<Student> findAll();
	Set<Student> getAllByTeacher(Long teacherId);
	Set<Student> getAllByClassId(Long classId);
	Student getById(Long id);
	Student save(Student student);
	Student update(Student student);
	void deleteById(Long id);
	boolean existsById(Long id);

}
