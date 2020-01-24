package com.gladguys.alucoapi.entities.dto;

import lombok.Data;

@Data
public class UserDTO {

	private Long teacherId;
	private Long userId;
	private String email;
	private String name;
	private String photoUrl;


}
