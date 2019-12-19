package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import lombok.Data;

import java.util.List;

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
		switch (period) {
			case 1:
				return this.periodOne.getExamsPeriod();
			case 2:
				return this.periodTwo.getExamsPeriod();
			case 3:
				return this.periodThree.getExamsPeriod();
			case 4:
				return this.periodFour.getExamsPeriod();
			default:
				return null;
		}
	}

	public void calculateFinalAverage() {
		this.average =
				(this.periodOne.getAverage()
				+ this.periodTwo.getAverage()
				+ this.periodThree.getAverage()
				+ this.periodFour.getAverage())/4;
	}
}

