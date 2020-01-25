package com.gladguys.alucoapi.repositories.customs;

import com.gladguys.alucoapi.entities.dto.UserDTO;

public interface CustomUserRepository {

	Long getTeacherIdByUsername(String username);
	UserDTO getUserTeacherByEmail(String email);
}
