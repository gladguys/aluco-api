package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface ExamService {

	ExamDTO getById(Long id);

	List<ExamDTO> getAllByFilterClassOrTeacher(ExamFilter filter);

	Exam saveOrUpdate(ExamDTO examDTO);

	boolean exists(Long id);

	void deleteById(Long id);

    Set<Long> getAllByClassId(Long classId);
}
