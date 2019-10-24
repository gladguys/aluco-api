package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface ClassService {

	ClassDTO getById(Long id) throws Exception;

	Set<ClassDTO> getAllByTeacher(Long teacherId) throws Exception;

	ClassDTO saveOrUpdate(ClassDTO c);

	void addStudentsIntoClass(Set<StudentDTO> studentDTOS, Long id) throws Exception;
}
