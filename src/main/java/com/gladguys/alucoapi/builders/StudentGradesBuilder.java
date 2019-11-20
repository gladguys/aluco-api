package com.gladguys.alucoapi.builders;

import com.gladguys.alucoapi.entities.StudentGrades;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.helpers.GradeHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StudentGradesBuilder {

	public static StudentGrades build(List<ExamGradeDTO> examGradeDTOS) {
		StudentGrades sg = new StudentGrades();
		sg.setStudentName(examGradeDTOS.get(0).getStudentName());
		sg.setExams(
				examGradeDTOS.parallelStream()
						.map(ExamGradeDTO::buildForGradesBoard)
						.collect(Collectors.toList()));

		sg.setAverage(GradeHelper.getAverageGrade(examGradeDTOS.stream().filter(e -> !e.getExamDate().isAfter(LocalDate.now()))
				.collect(Collectors.toList())));
		
		return sg;
	}
}
