package com.gladguys.alucoapi.services.impl;

import java.util.List;

import com.gladguys.alucoapi.entities.dto.StudentClassObservationDTO;
import org.springframework.stereotype.Service;

import com.gladguys.alucoapi.entities.StudentClassObservation;
import com.gladguys.alucoapi.repositories.StudentClassObservationRepository;
import com.gladguys.alucoapi.services.StudentClassObservationService;

@Service
public class StudentClassObservationServiceImpl implements StudentClassObservationService {

	private StudentClassObservationRepository studentClassObservationRepository;

	public StudentClassObservationServiceImpl(StudentClassObservationRepository studentClassObservationRepository) {
		this.studentClassObservationRepository = studentClassObservationRepository;
	}

	@Override
	public StudentClassObservation save(StudentClassObservation studentClassObservation) {
		return this.studentClassObservationRepository.save(studentClassObservation);
	}

	@Override
	public List<StudentClassObservationDTO> getStudentObservation(Long idStudent) {
		return this.studentClassObservationRepository.getStudentObservation(idStudent);
	}
}
