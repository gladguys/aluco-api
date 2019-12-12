package com.gladguys.alucoapi.builders;

import com.gladguys.alucoapi.entities.StudentGrades;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.helpers.GradeHelper;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentGradesBuilder {

	public static StudentGrades build(List<ExamGradeDTO> examGradeDTOS) {
		StudentGrades sg = new StudentGrades();
		sg.setStudentName(examGradeDTOS.get(0).getStudentName());

		sg.setExamsPeriodOne(examGradeDTOS.parallelStream()
				.filter(exam -> exam.getPeriodYear() == 1)
				.map(ExamGradeDTO::buildForGradesBoard)
				.sorted(Comparator.comparing(ExamGradeDTO::getExamDate, Comparator.nullsLast(Comparator.reverseOrder())))
				.collect(Collectors.toList()));

		sg.setExamsPeriodTwo(examGradeDTOS.parallelStream()
				.filter(exam -> exam.getPeriodYear() == 2)
				.map(ExamGradeDTO::buildForGradesBoard)
				.sorted(Comparator.comparing(ExamGradeDTO::getExamDate, Comparator.nullsLast(Comparator.reverseOrder())))
				.collect(Collectors.toList()));

		sg.setExamsPeriodThree(examGradeDTOS.parallelStream()
				.filter(exam -> exam.getPeriodYear() == 3)
				.map(ExamGradeDTO::buildForGradesBoard)
				.sorted(Comparator.comparing(ExamGradeDTO::getExamDate, Comparator.nullsLast(Comparator.reverseOrder())))
				.collect(Collectors.toList()));

		sg.setExamsPeriodFour(examGradeDTOS.parallelStream()
				.filter(exam -> exam.getPeriodYear() == 4)
				.map(ExamGradeDTO::buildForGradesBoard)
				.sorted(Comparator.comparing(ExamGradeDTO::getExamDate, Comparator.nullsLast(Comparator.reverseOrder())))
				.collect(Collectors.toList()));

		if (sg.getExamsPeriodOne().size() > 0) {
			Double averagePeriodOne = 0.0;
			List<ExamGradeDTO> regularExams = sg.getExamsPeriodOne()
					.stream()
					.filter(e -> !e.isRecExam())
					.collect(Collectors.toList());

			averagePeriodOne = GradeHelper.getAverageGrade(regularExams.stream().filter(e -> !e.getExamDate().isAfter(LocalDate.now()))
					.collect(Collectors.toList()));

			if (sg.getExamsPeriodOne().stream().anyMatch(ExamGradeDTO::isRecExam)) {
				Double gradeRec = sg.getExamsPeriodOne()
						.stream()
						.filter(ExamGradeDTO::isRecExam)
						.findFirst().orElse(null)
						.getGrade();

				averagePeriodOne = (averagePeriodOne + gradeRec) / 2;
			}

			sg.setAveragePeriodOne(averagePeriodOne);
		}

		if (sg.getExamsPeriodTwo().size() > 0) {
			Double averagePeriodTwo = 0.0;
			List<ExamGradeDTO> regularExams = sg.getExamsPeriodTwo()
					.stream()
					.filter(e -> !e.isRecExam())
					.collect(Collectors.toList());

			averagePeriodTwo = GradeHelper.getAverageGrade(regularExams.stream().filter(e -> !e.getExamDate().isAfter(LocalDate.now()))
					.collect(Collectors.toList()));

			if (sg.getExamsPeriodTwo().stream().anyMatch(ExamGradeDTO::isRecExam)) {
				Double gradeRec = sg.getExamsPeriodTwo()
						.stream()
						.filter(ExamGradeDTO::isRecExam)
						.findFirst().orElse(null)
						.getGrade();

				averagePeriodTwo = (averagePeriodTwo + gradeRec) / 2;
			}

			sg.setAveragePeriodTwo(averagePeriodTwo);
		}

		if (sg.getExamsPeriodThree().size() > 0) {
			Double averagePeriodThree = 0.0;
			List<ExamGradeDTO> regularExams = sg.getExamsPeriodThree()
					.stream()
					.filter(e -> !e.isRecExam())
					.collect(Collectors.toList());

			averagePeriodThree = GradeHelper.getAverageGrade(regularExams.stream().filter(e -> !e.getExamDate().isAfter(LocalDate.now()))
					.collect(Collectors.toList()));

			if (sg.getExamsPeriodThree().stream().anyMatch(ExamGradeDTO::isRecExam)) {
				Double gradeRec = sg.getExamsPeriodThree()
						.stream()
						.filter(ExamGradeDTO::isRecExam)
						.findFirst().orElse(null)
						.getGrade();

				averagePeriodThree = (averagePeriodThree + gradeRec) / 2;
			}

			sg.setAveragePeriodThree(averagePeriodThree);
		}

		if (sg.getExamsPeriodFour().size() > 0) {
			Double averagePeriodFour = 0.0;
			List<ExamGradeDTO> regularExams = sg.getExamsPeriodFour()
					.stream()
					.filter(e -> !e.isRecExam())
					.collect(Collectors.toList());

			averagePeriodFour = GradeHelper.getAverageGrade(regularExams.stream().filter(e -> !e.getExamDate().isAfter(LocalDate.now()))
					.collect(Collectors.toList()));

			if (sg.getExamsPeriodFour().stream().anyMatch(ExamGradeDTO::isRecExam)) {
				Double gradeRec = sg.getExamsPeriodFour()
						.stream()
						.filter(ExamGradeDTO::isRecExam)
						.findFirst().orElse(null)
						.getGrade();

				averagePeriodFour = (averagePeriodFour + gradeRec) / 2;
			}

			sg.setAveragePeriodFour(averagePeriodFour);
		}

		sg.setAverage(
				(sg.getAveragePeriodOne() + sg.getAveragePeriodTwo() + sg.getAveragePeriodThree() + sg.getAveragePeriodFour()) / 4);

		return sg;
	}
}
