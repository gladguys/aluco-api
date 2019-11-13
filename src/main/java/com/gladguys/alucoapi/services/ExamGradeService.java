package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.ExamGrade;
import com.gladguys.alucoapi.entities.StudentGrades;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;

import java.util.List;

public interface ExamGradeService {

	void saveAllGrades(List<ExamGradeDTO> gradesDTO);
	List<ExamGradeDTO> getGradesByExamId(Long id);
	void deleteGrade(ExamGradeDTO dto);
	List<StudentGrades> getGradeBoardFromClass(Long classId);
	void deleteByClassId(Long id);
}

