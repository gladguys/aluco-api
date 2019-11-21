package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.enums.ProfileEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import java.time.LocalDate;

@Entity(name = "user_aluco")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	@Email
	@Column(unique = true)
	private String email;

	private String password;

	@CreatedDate
	@Column(name = "create_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate createDate;

	@Column(name = "profile")
	private ProfileEnum profileEnum;

}
