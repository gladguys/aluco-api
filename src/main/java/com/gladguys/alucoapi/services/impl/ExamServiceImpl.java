package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.repositories.ExamRepository;
import com.gladguys.alucoapi.services.ExamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	public List<ExamDTO> getAllByTeacherId(Long teacherId) throws Exception {
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
