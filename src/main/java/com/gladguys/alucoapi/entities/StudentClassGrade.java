package com.gladguys.alucoapi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.math.BigDecimal;

@EqualsAndHashCode
@Entity(name = "student_class_grade")
public @Data class StudentClassGrade {

	@Id
	private Long id;

	private BigDecimal grade;
	private Long weight;
}
