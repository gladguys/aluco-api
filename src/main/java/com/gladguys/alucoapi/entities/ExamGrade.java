package com.gladguys.alucoapi.entities;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity(name = "exam_grade")
public class ExamGrade {

	@EmbeddedId
	private ExamGradeKey id;

	private Double grade;

	public ExamGradeKey getId() {
		return id;
	}

	public void setId(ExamGradeKey id) {
		this.id = id;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}
}
