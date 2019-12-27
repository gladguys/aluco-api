package com.gladguys.alucoapi.services;

import java.util.List;

import com.gladguys.alucoapi.entities.dto.StudentClassObservationDTO;
import org.springframework.stereotype.Component;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.StudentClassObservation;
import com.gladguys.alucoapi.entities.dto.StudentDTO;

@Component
public interface StudentClassObservationService {

	boolean existsById(Long id);
	StudentClassObservation save(StudentClassObservation studentClassObservation);
	void deleteById(Long id);
	List<StudentClassObservationDTO> getStudentObservation(Long idStudent);

}
