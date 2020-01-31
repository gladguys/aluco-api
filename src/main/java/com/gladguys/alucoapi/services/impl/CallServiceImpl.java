package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.entities.dto.CallDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.repositories.CallRepository;
import com.gladguys.alucoapi.services.CallService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CallServiceImpl implements CallService {

	private CallRepository callRepository;

	CallServiceImpl(CallRepository callRepository) {
		this.callRepository = callRepository;
	}

	@Override
	public Call getById(Long id) {
		return callRepository.getOne(id);
	}

	@Override
	public List<CallDTO> getAllByClassAndDate(Long classId, LocalDate date) {
		return this.callRepository.getCallsByClassIdAndDate(classId, date);
	}

	@Override
	public List<CallDTO> getAllByStudent(Long studentId, Long classId) {
		if (studentId != null) {
			return this.callRepository.getAllByStudentIdAndClassId(studentId, classId);
		}
		throw new ApiResponseException("Studante enviado sem id!");
	}

	public CallDTO save(CallDTO dto) throws Exception {
		Call callSaved = new Call();
		if (dto.getId() == null)
			callSaved = this.callRepository.save(dto.toEntity());
		else {
			try {
				CallDTO callToUpdate = this.callRepository.getById(dto.getId());
				dto.setClassId(callToUpdate.getClassId());
				dto.setStudentId(callToUpdate.getStudentId());

				callSaved = this.callRepository.save(dto.toEntity());
			} catch (EmptyResultDataAccessException e) {
				throw new ApiResponseException("não existe chamada com esse id");
			}
		}

		return callSaved.toDTO();
	}

	@Override
	public List<CallDTO> getCallsForDailyReport(Long classId) {
		return this.callRepository.getCallsForDailyReport(classId);
	}

	public void deleteAllByClassId(Long classId) {
		this.callRepository.deleteByClassId(classId);
	}
}
