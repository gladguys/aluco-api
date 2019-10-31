package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomExamRepository {

	List<ExamDTO> getByFilters(ExamFilter examFilter);

	ExamDTO getExamWithClass(Long examId);
}
