package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.User;
import com.gladguys.alucoapi.repositories.UserRepository;
import com.gladguys.alucoapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User getById(Long id) {
		return null;
	}

	@Override
	public User update(User user) {
		return null;
	}

	@Override
	public void deleteById(Long id) {

	}

}
