package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.ClassDTO;

import java.util.List;

public interface CustomClassRepository {
	
	List<ClassDTO> getAllByTeacherId(Long teacherId);
	void deleteStudentFromClass(Long studentId, Long classId, List<Long> examsId);
}
