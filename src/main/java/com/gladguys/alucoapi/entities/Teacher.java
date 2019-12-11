package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.time.LocalDate;

@Entity(name = "teacher")
@Data
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "photo_url")
	private String photoURL;

	@CreatedDate
	@Column(name = "create_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate createDate;

	@OneToOne
	@JoinColumn(name = "user_aluco_id")
	private User user;

}
