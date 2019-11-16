package com.gladguys.alucoapi.helpers;

import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;

import java.util.List;

public class GradeHelper {

	public static Double getAverageGrade(List<ExamGradeDTO> examGradesDTO) {

		return getSumGrades(examGradesDTO) / getSumWeights(examGradesDTO);
	}

	private static int getSumWeights(List<ExamGradeDTO> examGradesDTO) {
		return examGradesDTO
					.parallelStream()
					.map(ExamGradeDTO::getWeight)
					.reduce(Integer::sum).get();
	}

	private static Double getSumGrades(List<ExamGradeDTO> examGradesDTO) {
		return examGradesDTO
					.parallelStream()
					.filter(e -> e.getGrade() != null)
					.map(dto -> dto.getGrade() * dto.getWeight())
					.reduce(Double::sum)
					.orElse(0);
	}
}
