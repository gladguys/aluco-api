package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.exception.notfound.ExamNotFoundException;
import com.gladguys.alucoapi.repositories.ExamRepository;
import com.gladguys.alucoapi.services.ExamService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

	private ExamRepository examRepository;

	public ExamServiceImpl(ExamRepository examRepository) {
		this.examRepository = examRepository;
	}

	@Override
	public ExamDTO getById(Long id) {
		try {
			if (id == null) throw new ApiResponseException("Prova é obrigatória");
			ExamDTO dto = this.examRepository.getById(id);
			if (dto == null) throw new ExamNotFoundException(id);
			return dto;
		} catch (EmptyResultDataAccessException e) {
			throw new ExamNotFoundException(id);
		}
	}

	@Override
	public List<ExamDTO> getAllByFilterClassOrTeacher(ExamFilter filter) {
		return this.examRepository.getByFilters(filter);
	}

	@Override
	public Exam saveOrUpdate(ExamDTO examDTO) {
		return this.examRepository.save(examDTO.toEntity());
	}

	@Override
	public boolean exists(Long id) {
		return this.examRepository.existsById(id);
	}

	@Override
	public void deleteById(Long id) {
		this.examRepository.deleteById(id);
	}

	@Override
	public Set<Long> getAllByClassId(Long classId) {
		return this.examRepository.getAllByClassId(classId);
	}

}
