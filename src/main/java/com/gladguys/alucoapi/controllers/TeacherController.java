package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@PostMapping
	public ResponseEntity<Teacher> signup(@RequestBody NewUserDTO)
}
