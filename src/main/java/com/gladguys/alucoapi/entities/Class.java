package com.gladguys.alucoapi.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import java.util.Date;

@Entity(name = "class")
public @Data class Class {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Nome deve ser informado.")
	private String name;

	private String description;

	@CreatedDate
	@Column(name = "create_date")
	private Date createDate;

}
