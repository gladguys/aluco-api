package com.gladguys.alucoapi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import java.math.BigDecimal;

@Embeddable
@Data
public class ExamGradeKey {


	private BigDecimal grade;


	@Column(name = "student_id")
	private Student student;

	@Column(name = "")
	private Exam exam;

}
