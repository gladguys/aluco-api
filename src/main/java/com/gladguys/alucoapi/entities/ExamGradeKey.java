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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@Data
public class ExamGradeKey implements Serializable {


	@Column(name = "student_id")
	private Long studentId;

	@Column(name = "")
	private Long examId;

	public ExamGradeKey() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass())
			return false;

		ExamGradeKey that = (ExamGradeKey) o;
		return Objects.equals(studentId, that.studentId) &&
				Objects.equals(examId, that.examId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentId, examId);
	}

}
