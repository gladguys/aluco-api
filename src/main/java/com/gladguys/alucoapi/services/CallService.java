package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.entities.dto.CallDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface CallService {

	Call getById(Long id);
	List<CallDTO> getAllByClassAndDate(Long classId, LocalDate date);
	List<CallDTO> getAllByStudent(Long studentId, Long classId);
	CallDTO save(CallDTO callDTO) throws Exception;

	List<CallDTO> getCallsForDailyReport(Long classId);

	void deleteAllByClassId(Long classId);

}
