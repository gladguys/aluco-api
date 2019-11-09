package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface ExamService {

	Exam getById(Long id);

	List<ExamDTO> getAllByTeacherId(Long teacherId);

	Exam saveOrUpdate(ExamDTO examDTO);

	void deleteById(Long id);

    Set<Long> getAllByClassId(Long classId);
}
