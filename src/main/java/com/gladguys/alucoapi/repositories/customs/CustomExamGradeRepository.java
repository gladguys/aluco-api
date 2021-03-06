package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;

import java.util.List;

public interface CustomExamGradeRepository {

	List<ExamGradeDTO> getGradesByExamId(Long id);

	List<ExamGradeDTO> getGradesBoard(Long classId, Long studentId);
	void deleteByClassId(Long id);

	List<ExamGradeDTO> getGradesByStudentId(Long classId, Long studentId);
}
