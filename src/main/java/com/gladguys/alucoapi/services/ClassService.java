package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Class;
import org.springframework.stereotype.Component;

@Component
public interface ClassService {

	Class getById(Long id);
	Class update(Class classUpdate);
	void deleteById(Long id);

}