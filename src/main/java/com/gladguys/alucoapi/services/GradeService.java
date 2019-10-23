package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.entities.dto.GradeDTO;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface GradeService {

	Grade getById(Long id) throws Exception;

	Set<Grade> getAllGradesByExam(Long examId) throws Exception;

	Grade saveOrUpdate(GradeDTO gradeDTO);

	void deleteById(Long id) throws Exception;

}
