package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

	User getById(Long id);
	User findByEmail(String email);
	User update(User user);
	void deleteById(Long id);

}
