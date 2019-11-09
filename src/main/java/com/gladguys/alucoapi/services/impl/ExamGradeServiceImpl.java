package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.ExamGrade;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.repositories.ExamGradeRepository;
import com.gladguys.alucoapi.services.ExamGradeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamGradeServiceImpl implements ExamGradeService {

	ExamGradeRepository repository;

	public ExamGradeServiceImpl(ExamGradeRepository repository) {
		this.repository = repository;
	}

	@Override
	public void saveAllGrades(List<ExamGradeDTO> gradesDTO) {
		List<ExamGrade> grades = new ArrayList<>();
		gradesDTO.forEach( dto -> grades.add(dto.toEntity()));
		this.repository.saveAll(grades);
	}
}
