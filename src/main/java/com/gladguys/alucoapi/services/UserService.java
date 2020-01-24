package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.User;
import com.gladguys.alucoapi.entities.dto.UserDTO;

public interface UserService {

	User findByEmail(String email);
	User createOrUpdate(User user);
	Long getTeacherIdByUsername(String username);
	boolean existsByEmail(String email);
	UserDTO getUserTeacherByEmail(String email);

}
