package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
public class LessonPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	@Lob
	private String metodology;

	@Lob
	private String classwork;

	@Lob
	private String homework;

	@Lob
	private String notes;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate lessonDate;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class aClass;

}
