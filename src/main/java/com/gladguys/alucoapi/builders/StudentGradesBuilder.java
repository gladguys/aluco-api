package com.gladguys.alucoapi.builders;

import com.gladguys.alucoapi.entities.PeriodContent;
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

		PeriodContent periodFirst = new PeriodContent();
		periodFirst.setExamsPeriod(periodsMap.get(1));
		sg.setPeriodOne(periodFirst);
		periodFirst.setAverage(calculateAveragePerPeriod(sg, sg.getExamsPeriod(1)));

		PeriodContent periodTwo = new PeriodContent();
		periodTwo.setExamsPeriod(periodsMap.get(2));
		sg.setPeriodTwo(periodTwo);
		periodTwo.setAverage(calculateAveragePerPeriod(sg, sg.getExamsPeriod(2)));

		PeriodContent periodThree = new PeriodContent();
		periodThree.setExamsPeriod(periodsMap.get(3));
		sg.setPeriodThree(periodThree);
		periodThree.setAverage(calculateAveragePerPeriod(sg, sg.getExamsPeriod(3)));

		PeriodContent periodFour = new PeriodContent();
		periodFour.setExamsPeriod(periodsMap.get(4));
		sg.setPeriodFour(periodFour);
		periodFour.setAverage(calculateAveragePerPeriod(sg, sg.getExamsPeriod(4)));

		sg.calculateFinalAverage();

		return sg;
	}

	private static Double calculateAveragePerPeriod(StudentGrades sg, List<ExamGradeDTO> examsFromPeriod) {
		Double average = 0.0;
		if (examsFromPeriod != null && examsFromPeriod.size() > 0) {
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
		} else {
			average = null;
		}
		return average;
	}
}
