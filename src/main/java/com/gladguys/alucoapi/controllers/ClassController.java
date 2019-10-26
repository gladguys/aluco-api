package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Class;
import com.gladguys.alucoapi.entities.StudentWrapper;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

	private ClassService classService;
	private JwtTokenUtil jwtTokenUtil;

	public ClassController(ClassService classService, JwtTokenUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.classService = classService;
	}

	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<Set<ClassDTO>> getAllByTeacher(@PathVariable("teacherId") Long teacherId) {
		try {
			Set<ClassDTO> classes = this.classService.getAllByTeacher(teacherId);
			return ResponseEntity.ok(classes);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping
	public ResponseEntity<Set<ClassDTO>> getAll(HttpServletRequest request) {
		try {
			Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
			Set<ClassDTO> classes = this.classService.getAllByTeacher(teacherId);
			return ResponseEntity.ok(classes);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClassDTO> getById(@PathVariable("id") Long id) throws Exception {
		ClassDTO classFound = this.classService.getById(id);
		return ResponseEntity.ok(classFound);
	}

	@PostMapping
	public ResponseEntity<ClassDTO> save(@RequestBody ClassDTO dto) {
		ClassDTO classDTO = this.classService.saveOrUpdate(dto);
		return  ResponseEntity.ok(classDTO);
	}

	@PutMapping
	public ResponseEntity<ClassDTO> update(@RequestBody ClassDTO dto) {
		ClassDTO classDTO = this.classService.saveOrUpdate(dto);
		return  ResponseEntity.ok(classDTO);
	}

	@PostMapping("/{id}/students")
	public ResponseEntity saveStudentsForClass(@PathVariable("id") Long id, @RequestBody StudentWrapper wrapper) {

		try {
			this.classService.addStudentsIntoClass(wrapper.getStudentDTOS(), id);
			return ResponseEntity.ok("estudantes adicionados a classe");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
