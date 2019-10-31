package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.entities.dto.GradeDTO;

import java.util.List;

public interface CustomGradeRepository {

	List<GradeDTO> getAllGradesByExam(Long examId);
	List<GradeDTO> getAllGradesByStudent(Long examId);
	List<GradeDTO> getAllGradesByClass(Long classId);

}
