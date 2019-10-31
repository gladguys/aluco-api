package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.entities.dto.GradeDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GradeService {

	Grade getById(Long id) throws Exception;

	List<GradeDTO> getAllGradesByExam(Long examId) throws Exception;

	List<GradeDTO> getAllGradesByStudent(Long student) throws Exception;

	Grade saveOrUpdate(GradeDTO gradeDTO) throws Exception;

	void deleteById(Long id) throws Exception;

	List<GradeDTO> getAllGradesByClass(Long classId) throws Exception;
}
