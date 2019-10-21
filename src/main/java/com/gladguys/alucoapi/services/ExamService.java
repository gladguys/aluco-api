package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Exam;

public interface ExamService {

	Exam getById(Long id);
	Exam update(Exam exam);
	void deleteById(Long id);

}
