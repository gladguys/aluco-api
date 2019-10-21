package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Grade;
import org.springframework.stereotype.Component;

@Component
public interface GradeService {

	Grade getById(Long id);
	Grade update(Grade grade);
	void deleteById(Long id);

}
