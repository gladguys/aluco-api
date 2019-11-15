package com.gladguys.alucoapi.entities.filters;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LessonPlanFilter {

	public LessonPlanFilter(Long classId, LocalDate lessonDate) {
		this.classId = classId;
		this.lessonDate = lessonDate;
	}

	private Long classId;
	private LocalDate lessonDate;
}
