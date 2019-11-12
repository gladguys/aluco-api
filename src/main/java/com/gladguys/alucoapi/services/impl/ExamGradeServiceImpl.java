package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.ExamGrade;
import com.gladguys.alucoapi.entities.StudentGrades;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.repositories.ExamGradeRepository;
import com.gladguys.alucoapi.services.ExamGradeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExamGradeServiceImpl implements ExamGradeService {

	ExamGradeRepository repository;

	public ExamGradeServiceImpl(ExamGradeRepository repository) {
		this.repository = repository;
	}

	@Override
	public void saveAllGrades(List<ExamGradeDTO> gradesDTO) {

		this.repository
				.saveAll(
						gradesDTO.stream()
								.map(ExamGradeDTO::toEntity)
								.collect(Collectors.toList()));
	}

	@Override
	public List<ExamGradeDTO> getGradesByExamId(Long id) {
		return this.repository.getGradesByExamId(id);
	}

	@Override
	public void deleteGrade(ExamGradeDTO dto) {
		if (dto.getStudentId() == null) throw new ApiResponseException("Id do aluno não informado");
		if (dto.getExamId() == null) throw new ApiResponseException("Id da prova não informado");
		this.repository.save(dto.toEntity());
	}

	@Override
	public List<StudentGrades> getGradeBoardFromClass(Long classId) {

		List<StudentGrades> studentsGrades = new ArrayList<>();

		List<ExamGradeDTO> examsGrades = this.repository.getGradesBoard(classId);
		Map<Long, List<ExamGradeDTO>> collect = examsGrades.stream().collect(Collectors.groupingBy(ExamGradeDTO::getStudentId));
		collect.forEach((aLong, examGradeDTOS) -> {
			StudentGrades sg = new StudentGrades();
			sg.setStudentName(examGradeDTOS.get(0).getStudentName());
			sg.setExams(
					examGradeDTOS.stream()
							     .map(e -> new ExamGradeDTO(e.getExamId(), e.getExamName(), e.getGrade()))
							     .collect(Collectors.toList()));

			//TODO: create logic to generate status
			sg.setStatus("EM ANDAMENTO");

			studentsGrades.add(sg);
		} );

		return studentsGrades;
	}

}
