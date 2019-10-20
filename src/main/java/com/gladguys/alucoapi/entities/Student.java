package com.gladguys.alucoapi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.util.Date;

@EqualsAndHashCode
@Entity(name = "student")
public @Data class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "Nome deve ser informado.")
	private String name;

	@Email
	private String email;

	@Column(name = "photo_url")
	private String photoURL;

	@Column(name = "date_of_birth")
	private Date dateBirth;

	private String phone;

}

