package com.gladguys.alucoapi.entities.filters;

import lombok.Data;

@Data
public class ExamFilter {

	private Long id;
	private String name;
	private Long classId;
	private Long teacherId;

	public ExamFilter(String name, Long classId, Long teacherId) {
		this.name = name;
		this.classId = classId;
		this.teacherId = teacherId;
	}
}
