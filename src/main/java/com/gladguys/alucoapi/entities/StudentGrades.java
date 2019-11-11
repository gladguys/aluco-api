package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import lombok.Data;

import java.util.List;

@Data
public class StudentGrades {

	private List<ExamGradeDTO> exams;
	private String status;
	private String studentName;
}

