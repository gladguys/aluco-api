package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Exam;
import org.springframework.stereotype.Component;

@Component
public interface ExamService {

	Exam getById(Long id);
	Exam update(Exam exam);
	void deleteById(Long id);

}
