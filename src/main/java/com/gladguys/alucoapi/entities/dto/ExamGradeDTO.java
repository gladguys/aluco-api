package com.gladguys.alucoapi.entities.dto;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.ExamGrade;
import com.gladguys.alucoapi.entities.ExamGradeKey;
import com.gladguys.alucoapi.entities.Student;
import lombok.Data;

@Data
public class ExamGradeDTO {

	private Long examId;
	private String examName;
	private int weight;
	private Long studentId;
	private String studentName;
	private Double grade;

	public ExamGradeDTO() {
	}

	public ExamGradeDTO(Long examId, String examName, Double grade, int weight) {
		this.examId = examId;
		this.examName = examName;
		this.grade = grade;
		this.weight = weight;
	}

	public  ExamGradeDTO buildForGradesBoard() {
		return new ExamGradeDTO(this.examId, this.examName, this.grade, this.weight);
	}

	public ExamGrade toEntity() {
		ExamGrade examGrade = new ExamGrade();
		ExamGradeKey key = new ExamGradeKey();
		key.setExamId(examId);
		key.setStudentId(studentId);
		examGrade.setId(key);
		examGrade.setGrade(grade);

		return examGrade;
	}
}
