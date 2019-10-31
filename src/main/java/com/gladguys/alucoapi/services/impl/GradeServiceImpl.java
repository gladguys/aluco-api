package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.dto.GradeDTO;
import com.gladguys.alucoapi.repositories.GradeRepository;
import com.gladguys.alucoapi.services.ExamService;
import com.gladguys.alucoapi.services.GradeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class GradeServiceImpl implements GradeService {

	private GradeRepository gradeRepository;
	private ExamService examService;

	public GradeServiceImpl(GradeRepository gradeRepository, ExamService examService) {
		this.gradeRepository = gradeRepository;
		this.examService = examService;
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
	public List<GradeDTO> getAllGradesByClass(Long classId) throws Exception {

		if (classId == null) throw new  Exception("class id is null");
		return this.gradeRepository.getAllGradesByClass(classId);

	}

	@Override
	public Grade saveOrUpdate(GradeDTO gradeDTO) throws Exception {

		Grade grade = gradeDTO.toEntity();
		ExamDTO examOfGrade = this.examService.getExamWithClassIdByExamId(gradeDTO.getExamId());
		grade.setExam(examOfGrade.toEntity());

		return this.gradeRepository.save(grade);
	}

	@Override
	public void deleteById(Long id) throws Exception {

		if(id == null) throw new Exception("id null");

		this.gradeRepository.deleteById(id);
	}

}
