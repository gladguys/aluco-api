package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.ConfigClass;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.StudentAbsenceDTO;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface ClassService {

	ClassDTO getById(Long id);

	List<ClassDTO> getAllByTeacher(Long teacherId);

	ClassDTO saveOrUpdate(ClassDTO c);

	boolean exists(Long id);

	void deleteById(Long id);

	void defineNumberCalls(Long classId);

	void addStudentsIntoClass(Set<StudentDTO> studentDTOS, Long id);

	void deleteStudentFromClass(Long studentId, Long classId);

	boolean isClassFromTeacher(Long classId, Long teacherId);

	List<StudentAbsenceDTO> getAbsences(Long classId, Long studentId);

}
