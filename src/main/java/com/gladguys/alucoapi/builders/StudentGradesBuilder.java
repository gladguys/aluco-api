package com.gladguys.alucoapi.builders;

import com.gladguys.alucoapi.entities.StudentGrades;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.helpers.GradeHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentGradesBuilder {

	public static StudentGrades build(List<ExamGradeDTO> examGradeDTOS) {
		StudentGrades sg = new StudentGrades();
		sg.setStudentName(examGradeDTOS.get(0).getStudentName());

		Map<Integer, List<ExamGradeDTO>> periodsMap = examGradeDTOS.stream().collect(Collectors.groupingBy(ExamGradeDTO::getPeriodYear));

		sg.setExamsPeriodOne(periodsMap.get(1));
		sg.setExamsPeriodTwo(periodsMap.get(2));
		sg.setExamsPeriodThree(periodsMap.get(3));
		sg.setExamsPeriodFour(periodsMap.get(4));

		sg.setAveragePeriodOne(calculateAveragePerPeriod(sg, sg.getExamsPeriodOne()));
		sg.setAveragePeriodTwo(calculateAveragePerPeriod(sg, sg.getExamsPeriodTwo()));
		sg.setAveragePeriodThree(calculateAveragePerPeriod(sg, sg.getExamsPeriodThree()));
		sg.setAveragePeriodFour(calculateAveragePerPeriod(sg, sg.getExamsPeriodFour()));

		sg.setAverage(
				(sg.getAveragePeriodOne()
						+ sg.getAveragePeriodTwo()
						+ sg.getAveragePeriodThree()
						+ sg.getAveragePeriodFour()) / 4);

		return sg;
	}

	private static Double calculateAveragePerPeriod(StudentGrades sg, List<ExamGradeDTO> examsFromPeriod) {
		Double average = 0.0;
		if (examsFromPeriod.size() > 0) {
			List<ExamGradeDTO> regularExams = examsFromPeriod
					.stream()
					.filter(e -> !e.isRecExam())
					.collect(Collectors.toList());

			average = GradeHelper.getAverageGrade(regularExams.stream().filter(e -> !e.getExamDate().isAfter(LocalDate.now()))
					.collect(Collectors.toList()));

			ExamGradeDTO recExam = examsFromPeriod.stream().filter(ExamGradeDTO::isRecExam).findFirst().orElse(null);

			if (recExam != null && average < 8.0) {
				Double gradeRec = Objects.requireNonNull(recExam)
						.getGrade();
				average = (average + gradeRec) / 2;
			}
		}
		return average;
	}
}
