package com.gladguys.alucoapi.entities;

import com.gladguys.alucoapi.entities.enums.ProfileEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Date;

@EqualsAndHashCode
@Entity(name = "user_aluco")
public @Data class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Username deve ser informado.")
	private String username;

	@NotBlank(message = "Email deve ser informado.")
	@Email
	private String email;

	@NotBlank(message = "Password deve ser informado.")
	@Size(min = 6)
	private String password;

	@CreatedDate
	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "profile")
	private ProfileEnum profileEnum;

	@OneToOne(mappedBy = "user")
	private Teacher teacher;

}
