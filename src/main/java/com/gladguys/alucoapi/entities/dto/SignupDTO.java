package com.gladguys.alucoapi.entities.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDTO {

	@NotBlank(message = "Email deve ser informado.")
	@Email
	private String email;

	@NotBlank(message = "Password deve ser informado.")
	@Size(min = 6)
	private String password;
}
