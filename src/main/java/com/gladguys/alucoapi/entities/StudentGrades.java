package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import lombok.Data;

import java.util.List;

@Data
public class StudentGrades {

	private List<ExamGradeDTO> examsPeriodOne;
	private List<ExamGradeDTO> examsPeriodTwo;
	private List<ExamGradeDTO> examsPeriodThree;
	private List<ExamGradeDTO> examsPeriodFour;
	private String status;
	private String studentName;
	private Double average;
	private Double averagePeriodOne;
	private Double averagePeriodTwo;
	private Double averagePeriodThree;
	private Double averagePeriodFour;
}

