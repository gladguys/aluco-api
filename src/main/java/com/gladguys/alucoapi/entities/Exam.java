package com.gladguys.alucoapi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import java.util.Date;

@EqualsAndHashCode
@Entity(name = "exam")
public @Data class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Nome deve ser informado.")
	private String name;

	@NotBlank(message = "Descrição deve ser informada.")
	private String description;

	private Date date;

}
