package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import com.gladguys.alucoapi.repositories.ExamRepository;
import com.gladguys.alucoapi.services.ExamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

	private ExamRepository examRepository;

	public ExamServiceImpl(ExamRepository examRepository) {
		this.examRepository = examRepository;
	}

	@Override
	public Exam getById(Long id) throws Exception {

		if (id == null) throw new Exception("id null");
		return this.examRepository.findById(id).orElseThrow(Exception::new);
	}

	@Override
	public List<ExamDTO> getAllByTeacherId(ExamFilter filter) throws Exception {

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



}
