package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.StudentWrapper;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
			if(teacherId == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

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
	public ResponseEntity<ClassDTO> getById(@PathVariable("id") Long id) {
		try {
			if(id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

			ClassDTO classFound = this.classService.getById(id);
			return ResponseEntity.ok(classFound);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PostMapping
	public ResponseEntity<ClassDTO> save(@RequestBody ClassDTO dto, HttpServletRequest request) {
		try {
			if(dto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

			Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
			dto.setTeacherId(teacherId);

			ClassDTO classDTO = this.classService.saveOrUpdate(dto);

			return ResponseEntity.status(HttpStatus.CREATED).body(classDTO);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping
	public ResponseEntity<ClassDTO> update(@RequestBody ClassDTO dto, HttpServletRequest request) {

		try {
			if(dto == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			else if (dto.getId() == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

			boolean exists = this.classService.exists(dto.getId());
			if (!exists) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

			Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
			dto.setTeacherId(teacherId);

			ClassDTO classSaved = this.classService.saveOrUpdate(dto);
			return ResponseEntity.ok(classSaved);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			boolean exists = this.classService.exists(id);
			if (!exists) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

			this.classService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}


	@PostMapping("/{id}/students")
	public ResponseEntity saveStudentsForClass(@PathVariable("id") Long id, @RequestBody StudentWrapper wrapper) {
		try {
			if(id == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			if(wrapper == null || wrapper.getStudentDTOS().isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

			this.classService.addStudentsIntoClass(wrapper.getStudentDTOS(), id);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
