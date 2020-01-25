package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gladguys.alucoapi.entities.enums.GenderEnum;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "registration_number")
	private String registrationNumber;

	@NotBlank(message = "Nome deve ser informado.")
	private String name;

	@Email
	private String email;

	@Column(name = "photo_url")
	private String photoUrl;

	@Column(name = "date_of_birth")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateBirth;

	private String phone;

	private GenderEnum gender;

	@Column(name = "responsible_name")
	private String responsibleName;

	@Column(name = "responsible_phone")
	private String responsiblePhone;

	private String address;

	@Column(name = "previous_school")
	private String previousSchool;

	private String observation;

	private boolean isAEE;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

}
