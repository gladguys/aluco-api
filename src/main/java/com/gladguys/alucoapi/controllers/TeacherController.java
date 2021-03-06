package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Teacher;
import com.gladguys.alucoapi.entities.dto.SignupDTO;
import com.gladguys.alucoapi.services.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

	private TeacherService teacherService;

	public TeacherController(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@ApiOperation(value = "Cria um novo usuário e professor")
	@PostMapping
	public ResponseEntity<Teacher> signup(@RequestBody SignupDTO sign) {
		this.teacherService.createOrUpdate(sign);

		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
}
