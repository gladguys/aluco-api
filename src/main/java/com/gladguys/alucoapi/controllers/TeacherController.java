package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Teacher;
import com.gladguys.alucoapi.entities.dto.SignupDTO;
import com.gladguys.alucoapi.services.TeacherService;
import com.gladguys.alucoapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

	private UserService userService;
	private TeacherService teacherService;

	public TeacherController(UserService userService, TeacherService teacherService) {
		this.userService = userService;
		this.teacherService = teacherService;
	}

	@PostMapping
	public ResponseEntity<Teacher> signup(@RequestBody SignupDTO sign) {

		try {
			this.teacherService.createOrUpdate(sign);

			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}