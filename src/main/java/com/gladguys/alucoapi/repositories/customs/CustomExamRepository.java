package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface CustomExamRepository {

	List<ExamDTO> getByFilters(ExamFilter examFilter);

    Set<Long> getAllByClassId(Long classId);
}
