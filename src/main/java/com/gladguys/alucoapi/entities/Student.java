package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.Date;

@Entity(name = "student")
public @Data class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome deve ser informado.")
	private String name;

	@Email
	private String email;

	@Column(name = "photo_url")
	private String photoURL;

	@Column(name = "date_of_birth")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dateBirth;

	private String phone;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

}

