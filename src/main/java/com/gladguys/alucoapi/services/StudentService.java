package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentService {

	List<Student> findAll();
	List<StudentDTO> getAllByTeacher(Long teacherId);
	List<StudentDTO> getAllByClassId(Long classId);
	StudentDTO getById(Long id);
	Student save(Student student);
	Student update(Student student);
	void deleteById(Long id);
	boolean existsById(Long id);
	boolean isStudentFromTeacher(Long id, Long teacherId);
}
