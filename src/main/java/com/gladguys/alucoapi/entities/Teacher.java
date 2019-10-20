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
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import java.util.Date;

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
	private Date createDate;

	@OneToOne
	@JoinColumn(name = "user_aluco_id")
	private User user;

}
