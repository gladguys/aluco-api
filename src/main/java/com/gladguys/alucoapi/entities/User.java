package com.gladguys.alucoapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gladguys.alucoapi.entities.enums.ProfileEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.time.LocalDate;

@Entity(name = "user_aluco")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Username deve ser informado.")
	@Column(unique = true)
	private String username;

	@NotBlank(message = "Email deve ser informado.")
	@Email
	@Column(unique = true)
	private String email;

	@NotBlank(message = "Password deve ser informado.")
	@Size(min = 6)
	private String password;

	@CreatedDate
	@Column(name = "create_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate createDate;

	@Column(name = "profile")
	private ProfileEnum profileEnum;

}
