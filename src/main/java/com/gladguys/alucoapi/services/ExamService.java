package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExamService {

	Exam getById(Long id) throws Exception;

	List<ExamDTO> getAllByTeacherId(ExamFilter filter) throws Exception;

	Exam saveOrUpdate(ExamDTO examDTO);

	boolean exists(Long id);

	void deleteById(Long id);

	ExamDTO getExamWithClassIdByExamId(Long examId);
}
