package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.exception.notfound.ExamNotFoundException;
import com.gladguys.alucoapi.repositories.ExamRepository;
import com.gladguys.alucoapi.services.ExamService;
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
	public Exam getById(Long id) {
		if (id == null) throw new ApiResponseException("Prova é obrigatória");

		return this.examRepository.findById(id).orElseThrow(() -> new ExamNotFoundException(id));
	}

	@Override
	public List<ExamDTO> getAllByTeacherId(Long teacherId) {
		return null;
	}

	@Override
	public Exam saveOrUpdate(ExamDTO examDTO) {
		return this.examRepository.save(examDTO.toEntity());
	}

	@Override
	public void deleteById(Long id) {

	}

	@Override
	public Set<Long> getAllByClassId(Long classId) {
		return this.examRepository.getAllByClassId(classId);
	}

}
