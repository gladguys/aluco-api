package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.dto.ExamDTO;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "exam")
public @Data class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome deve ser informado.")
	private String name;

	@NotBlank(message = "Descrição deve ser informada.")
	private String description;

	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private Class classExam;

	@OneToMany(mappedBy = "exam")
	private Set<Grade> grades = new HashSet<>();

	public ExamDTO toDTO() {

		ExamDTO examDTO = new ExamDTO();
		examDTO.setId(id);
		examDTO.setName(name);
		examDTO.setDescription(description);
		examDTO.setDate(date);
		examDTO.setClassId(classExam.getId());
		examDTO.setTeacherId(classExam.getTeacher().getId());
		return examDTO;
	}
}
