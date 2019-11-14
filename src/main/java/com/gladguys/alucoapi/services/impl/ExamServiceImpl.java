package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.exception.notfound.ExamNotFoundException;
import com.gladguys.alucoapi.repositories.ExamRepository;
import com.gladguys.alucoapi.services.ExamGradeService;
import com.gladguys.alucoapi.services.ExamService;
import com.gladguys.alucoapi.services.StudentService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

	private ExamRepository examRepository;
	private StudentService studentService;
	private ExamGradeService examGradeService;

	public ExamServiceImpl(ExamRepository examRepository, StudentService studentService, ExamGradeService examGradeService) {
		this.examRepository = examRepository;
		this.studentService = studentService;
		this.examGradeService = examGradeService;
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
	@Transactional
	public Exam saveOrUpdate(ExamDTO examDTO) {

		Exam examSaved = this.examRepository.save(examDTO.toEntity());

		if (examDTO.getId() == null) {

			List<StudentDTO> students = this.studentService.getAllByClassId(examDTO.getClassId());
			this.examGradeService.saveAllGrades(students.stream().map(s ->
					new ExamGradeDTO(s.getId(), examSaved.getId(), null)).collect(Collectors.toList()));
		}

		return examSaved;

	}

	@Override
	public boolean exists(Long id) {
		return this.examRepository.existsById(id);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.examRepository.deleteExamGradeById(id);
	}

	@Override
	public Set<Long> getAllByClassId(Long classId) {
		return this.examRepository.getAllByClassId(classId);
	}

}
