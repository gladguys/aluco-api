package com.gladguys.alucoapi.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.LessonPlan;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LessonPlanDTO {

	private Long id;
	private String content;
	private String metodology;
	private String homework;
	private String classwork;
	private String notes;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate lessonDate;
	private Long classId;
	private Long teacherId;

	public LessonPlan toEntity() {
		LessonPlan lessonPlan = new LessonPlan();
		lessonPlan.setId(id);
		lessonPlan.setContent(content);
		lessonPlan.setHomework(homework);
		lessonPlan.setClasswork(classwork);
		lessonPlan.setMetodology(metodology);
		lessonPlan.setNotes(notes);
		lessonPlan.setLessonDate(lessonDate);

		Class aclass = new Class();
		aclass.setId(classId);
		lessonPlan.setClassPlan(aclass);

		return lessonPlan;
	}
}
