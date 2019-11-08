package com.gladguys.alucoapi.entities;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
@Data
public class ExamGrade {

	@EmbeddedId
	private ExamGradeKey id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("studentId")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("examId")
	private Exam exam;

	private Double grade;
}
