package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.repositories.CallRepository;
import com.gladguys.alucoapi.services.CallService;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public Set<Call> getAllByClassAndDate(Long classId, Date date) {
    	if (date == null) {
			date = new Date();
    	}

    	return this.callRepository.findAllByClassCallIdAndDate(classId, date);
    }

    @Override
    public Set<Call> getAllByStudent(Long studentId) {
        if (studentId != null) {
			return this.callRepository.getAllByStudentId(studentId);
        }

        return null;
    }

    @Override
    public void saveAll(Set<Call> calls) throws Exception {
    	if(calls != null) {
			this.callRepository.saveAll(calls);
		}
    	throw new Exception();
    }
}
