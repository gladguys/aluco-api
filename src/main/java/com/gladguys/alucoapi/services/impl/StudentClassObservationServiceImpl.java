package com.gladguys.alucoapi.services.impl;

import java.util.List;

import com.gladguys.alucoapi.entities.dto.StudentClassObservationDTO;
import com.gladguys.alucoapi.exception.notfound.StudentClassObservationNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
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
	public StudentClassObservation getById(Long id) {
		try {
			return this.studentClassObservationRepository.findById(id).orElse(null);
		} catch (EmptyResultDataAccessException e) {
			throw new StudentClassObservationNotFoundException(id);
		}
	}

	@Override
	public StudentClassObservation save(StudentClassObservation studentClassObservation) {
		return this.studentClassObservationRepository.save(studentClassObservation);
	}

	@Override
	public StudentClassObservation update(StudentClassObservation studentClassObservation) {
		return this.studentClassObservationRepository.save(studentClassObservation);
	}

	@Override
	public void deleteById(Long id) {
		this.studentClassObservationRepository.deleteById(id);
	}

	@Override
	public void deleteByClassId(Long id) {
		this.studentClassObservationRepository.deleteByClassId(id);
	}

	@Override
	public List<StudentClassObservationDTO> getStudentObservation(Long idStudent) {
		return this.studentClassObservationRepository.getStudentObservation(idStudent);
	}

	@Override
	public boolean existsById(Long id) {
		return this.studentClassObservationRepository.existsById(id);
	}
}
