package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.dto.LessonPlanDTO;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
public class LessonPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String notificationId;

	private String content;

	@Column(columnDefinition = "text")
	private String metodology;

	@Column(columnDefinition = "text")
	private String classwork;

	@Column(columnDefinition = "text")
	private String homework;

	@Column(columnDefinition = "text")
	private String notes;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate lessonDate;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class classPlan;

	public LessonPlanDTO toDTO() {
		LessonPlanDTO dto = new LessonPlanDTO();
		dto.setId(id);
		dto.setNotificationId(notificationId);
		dto.setContent(content);
		dto.setMetodology(metodology);
		dto.setHomework(homework);
		dto.setClasswork(classwork);
		dto.setLessonDate(lessonDate);
		dto.setNotes(notes);
		if (classPlan != null) {
			dto.setClassId(classPlan.getId());
		}
		return dto;
	}
}
