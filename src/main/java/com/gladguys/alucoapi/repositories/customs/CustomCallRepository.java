package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.CallDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CustomCallRepository {

	List<CallDTO> getCallsByClassIdAndDate(Long teacherId, LocalDate date);
	CallDTO getById(Long id);
	List<CallDTO> getAllByStudentIdAndClassId(Long studentId, Long classId);
	void deleteByClassId(Long classId);
}
