package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface ClassService {

	ClassDTO getById(Long id) throws Exception;

	List<ClassDTO> getAllByTeacher(Long teacherId) throws Exception;

	ClassDTO saveOrUpdate(ClassDTO c);

	boolean exists(Long id);

	void deleteById(Long id);

	void addStudentsIntoClass(Set<StudentDTO> studentDTOS, Long id) throws Exception;
}
