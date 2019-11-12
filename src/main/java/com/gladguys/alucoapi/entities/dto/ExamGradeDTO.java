package com.gladguys.alucoapi.entities.dto;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.ExamGrade;
import com.gladguys.alucoapi.entities.ExamGradeKey;
import com.gladguys.alucoapi.entities.Student;
import lombok.Data;

@Data
public class ExamGradeDTO {

	private Long examId;
	private Long studentId;
	private String studentName;
	private Double grade;

	public ExamGradeDTO(Long studentId, Long examId, Double grade) {
		this.studentId = studentId;
		this.examId = examId;
		this.grade = grade;
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
