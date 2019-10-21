package com.gladguys.alucoapi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Entity(name = "exam")
public @Data class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome deve ser informado.")
	private String name;

	@NotBlank(message = "Descrição deve ser informada.")
	private String description;

	private LocalDateTime date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private Class classExam;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@OneToMany(
		mappedBy = "exam",
		fetch = FetchType.LAZY)
	private List<Grade> grades = new ArrayList<>();

}
