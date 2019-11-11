package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.ExamGrade;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
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

	@Override
	public List<ExamGradeDTO> getGradesByExamId(Long id) {
		return this.repository.getGradesByExamId(id);
	}

	@Override
	public void deleteGrade(ExamGradeDTO dto) {
		if (dto.getStudentId() == null) throw new ApiResponseException("Id do aluno não informado");
		if (dto.getExamId() == null) throw new ApiResponseException("Id da prova não informado");
		this.repository.save(dto.toEntity());
	}
}
