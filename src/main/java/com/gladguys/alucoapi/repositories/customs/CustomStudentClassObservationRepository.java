package com.gladguys.alucoapi.repositories.customs;

import java.util.List;

import com.gladguys.alucoapi.entities.StudentClassObservation;
import com.gladguys.alucoapi.entities.dto.StudentClassObservationDTO;

public interface CustomStudentClassObservationRepository {

	List<StudentClassObservationDTO> getStudentObservation(Long idStudent);

}
