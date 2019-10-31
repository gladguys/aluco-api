package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.entities.dto.GradeDTO;
import com.gladguys.alucoapi.repositories.GradeRepository;
import com.gladguys.alucoapi.services.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

	private GradeRepository gradeRepository;

	public GradeServiceImpl(GradeRepository gradeRepository) {
		this.gradeRepository = gradeRepository;
	}

	@Override
	public Grade getById(Long id) throws Exception {

		if (id == null) throw new Exception("id null");

		return this.gradeRepository.findById(id).orElse(null);

	}

	@Override
	public List<GradeDTO> getAllGradesByExam(Long examId) throws Exception {

		if(examId == null) throw new Exception("id da turma não informado");

		return this.gradeRepository.getAllGradesByExam(examId);

	}

	@Override
	public List<GradeDTO> getAllGradesByStudent(Long studentId) throws Exception {

		if(studentId == null) throw new Exception("id do aluno não informado");

		return this.gradeRepository.getAllGradesByStudent(studentId);

	}

	@Override
	public Grade saveOrUpdate(GradeDTO gradeDTO) {

		return this.gradeRepository.save(gradeDTO.toEntity());
	}

	@Override
	public void deleteById(Long id) throws Exception {

		if(id == null) throw new Exception("id null");

		this.gradeRepository.deleteById(id);
	}

}
