package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.StudentDTO;

import java.util.List;

public interface CustomStudentRepository {

	List<StudentDTO> getAllByClassId(Long id);
	List<StudentDTO> getAllByTeacherId(Long id);
	StudentDTO getById(Long id);
	void deleteStudentFromAllClasses(Long studentId);

}
