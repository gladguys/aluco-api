package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class StudentGrades {

	private PeriodContent periodOne;
	private PeriodContent periodTwo;
	private PeriodContent periodThree;
	private PeriodContent periodFour;
	private String status;
	private String studentName;
	private Double average;

	public List<ExamGradeDTO> getExamsPeriod(int period) {
		List<ExamGradeDTO> exams = new ArrayList<>();
		switch (period) {
			case 1:
				exams = this.periodOne.getExamsPeriod();
				break;
			case 2:
				exams = this.periodTwo.getExamsPeriod();
				break;
			case 3:
				exams = this.periodThree.getExamsPeriod();
				break;
			case 4:
				exams = this.periodFour.getExamsPeriod();
				break;
		}
		if (exams != null && exams.stream().anyMatch(e -> e.getGrade() != null)) return exams;
		return null;
	}

	public void calculateFinalAverage() {

		int counter = 0;
		double finalGrade = 0.0;

		if (this.periodOne.getAverage() != null) {
			counter++;
			finalGrade += this.periodOne.getAverage();
		}
		if (this.periodTwo.getAverage() != null) {
			counter++;
			finalGrade += this.periodTwo.getAverage();
		}
		if (this.periodThree.getAverage() != null) {
			counter++;
			finalGrade += this.periodThree.getAverage();
		}
		if (this.periodFour.getAverage() != null) {
			counter++;
			finalGrade += this.periodFour.getAverage();
		}
		this.average = finalGrade / counter;
	}
}

