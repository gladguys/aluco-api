package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.entities.dto.CallDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public interface CallService {

	Call getById(Long id);
	Call update(Call call);
	List<CallDTO> getAllByClassAndDate(Long classId, LocalDate date);
	Set<Call> getAllByStudent(Long studentId);
	CallDTO save(CallDTO callDTO) throws Exception;

}
