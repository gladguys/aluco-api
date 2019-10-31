package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.User;

public interface UserService {

	User findByEmail(String email);
	User createOrUpdate(User user);
	Long getTeacherIdByUsername(String username);
	boolean existsByEmail(String email);

}
