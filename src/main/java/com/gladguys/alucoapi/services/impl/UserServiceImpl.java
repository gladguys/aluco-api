package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.User;
import com.gladguys.alucoapi.repositories.UserRepository;
import com.gladguys.alucoapi.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(user);
	}

	@Override
	public Long getTeacherIdByUsername(String username) {
		return this.userRepository.getTeacherIdByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return this.userRepository.existsByEmail(email);
	}

}
