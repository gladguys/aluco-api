package com.gladguys.alucoapi.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExamGradeKey implements Serializable {

	private Long studentId;

	private Long examId;

	public ExamGradeKey() {
	}

	public ExamGradeKey(Long studentId, Long examId) {
		this.studentId = studentId;
		this.examId = examId;
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

	public Long getStudentId() {
		return studentId;
	}

	public Long getExamId() {
		return examId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}
}
