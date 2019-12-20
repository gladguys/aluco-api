package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.entities.dto.CallDTO;
import com.gladguys.alucoapi.repositories.CallRepository;
import com.gladguys.alucoapi.services.CallService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    public Call update(Call call) {

    	return this.callRepository.save(call);
    }

    @Override
    public List<CallDTO> getAllByClassAndDate(Long classId, LocalDate date) {
    	return this.callRepository.getCallsByClassIdAndDate(classId, date);
    }

    @Override
    public Set<Call> getAllByStudent(Long studentId) {
        if (studentId != null) {
			return this.callRepository.getAllByStudentId(studentId);
        }

        return null;
    }

    public CallDTO save(CallDTO dto) throws Exception {

    	return this.callRepository.save(dto.toEntity()).toDTO();
    }
}
