package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;

import java.util.List;

public interface ExamGradeService {

	void saveAllGrades(List<ExamGradeDTO> gradesDTO);
	List<ExamGradeDTO> getGradesByExamId(Long id);
}