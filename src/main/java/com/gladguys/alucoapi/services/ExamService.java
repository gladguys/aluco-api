package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExamService {

	Exam getById(Long id) throws Exception;

	List<ExamDTO> getAllByTeacherId(Long teacherId) throws Exception;

	Exam saveOrUpdate(ExamDTO examDTO);

	void deleteById(Long id);

}
