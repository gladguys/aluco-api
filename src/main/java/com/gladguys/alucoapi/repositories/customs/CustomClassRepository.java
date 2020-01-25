package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.StudentAbsenceDTO;

import java.util.List;
import java.util.Set;

public interface CustomClassRepository {
	
	List<ClassDTO> getAllByTeacherId(Long teacherId);
	void deleteStudentFromClass(Long studentId, Long classId, Set<Long> examsId);
	boolean isClassFromTeacher(Long classId, Long teacherId);
	List<StudentAbsenceDTO> getAbsences(Long classId, Long studentId);
	void deleteClassById(Long id);
}
