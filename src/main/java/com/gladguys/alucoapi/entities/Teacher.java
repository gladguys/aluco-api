package com.gladguys.alucoapi.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Entity(name = "teacher")
public @Data class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome deve ser informado.")
	private String name;

	@Column(name = "photo_url")
	private String photoURL;

	@CreatedDate
	@Column(name = "create_date")
	private LocalDateTime createDate;

	@OneToOne
	@JoinColumn(name = "user_aluco_id")
	private User user;

	@OneToMany(mappedBy = "teacher")
	private List<Student> students = new ArrayList<>();

	@OneToMany(mappedBy = "teacher")
	private List<Exam> exams = new ArrayList<>();

}
