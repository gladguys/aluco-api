package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.entities.dto.GradeDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ExecutionException;

@Component
public interface GradeService {

	Grade getById(Long id) throws Exception;

	Set<Grade> getAllGradesByExam(Long examId) throws Exception;

	Set<Grade> getAllGradesByStudent(Long student) throws Exception;

	Grade saveOrUpdate(GradeDTO gradeDTO);

	void deleteById(Long id) throws Exception;

}
