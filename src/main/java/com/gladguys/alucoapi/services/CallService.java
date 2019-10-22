package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Call;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public interface CallService {

	Call getById(Long id);
	Call update(Call call);
	Set<Call> getAllByClassAndDate(Long classId, LocalDate date);
	Set<Call> getAllByStudent(Long studentId);
	void saveAll(Set<Call> calls) throws Exception;

}
