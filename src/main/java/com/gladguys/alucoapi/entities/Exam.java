package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.HashSet;
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

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate creationDate;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate examDate;

	private int weight;

	@ManyToOne
	@JoinColumn(name = "class_id")
	private Class classExam;

	public ExamDTO toDTO() {

		ExamDTO examDTO = new ExamDTO();
		examDTO.setId(id);
		examDTO.setName(name);
		examDTO.setDescription(description);
		examDTO.setCreationDate(creationDate);
		examDTO.setExamDate(examDate);
		examDTO.setWeight(weight);

		if(classExam != null) {
			examDTO.setClassId(classExam.getId());
		}

		return examDTO;
	}
}
