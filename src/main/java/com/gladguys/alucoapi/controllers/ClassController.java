package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.StudentWrapper;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.exception.ResponseException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.ClassService;
import com.gladguys.alucoapi.services.StudentService;
import io.swagger.annotations.ApiOperation;
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

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

	private ClassService classService;
	private StudentService studentService;
	private JwtTokenUtil jwtTokenUtil;

	public ClassController(ClassService classService, StudentService studentService, JwtTokenUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.classService = classService;
		this.studentService = studentService;
	}

	@ApiOperation(value = "Retorna as turmas de um professor específico")
	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<List<ClassDTO>> getAllByTeacher(@PathVariable("teacherId") Long teacherId) {
		try {
			if(teacherId == null) throw new ResponseException("Professor é obrigatório", HttpStatus.BAD_REQUEST);

			List<ClassDTO> classes = this.classService.getAllByTeacher(teacherId);
			return ResponseEntity.ok(classes);

		} catch (Exception e) {
			throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Retorna as turmas do professor logado")
	@GetMapping
	public ResponseEntity<List<ClassDTO>> getAll(HttpServletRequest request) {
		try {
			Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
			List<ClassDTO> classes = this.classService.getAllByTeacher(teacherId);
			return ResponseEntity.ok(classes);

		} catch (Exception e) {
			throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Retorna uma turma pelo código")
	@GetMapping("/{id}")
	public ResponseEntity<ClassDTO> getById(@PathVariable("id") Long id) {
		try {
			if(id == null) throw new ResponseException("Turma é obrigatória", HttpStatus.BAD_REQUEST);

			ClassDTO classFound = this.classService.getById(id);
			return ResponseEntity.ok(classFound);

		} catch (Exception e) {
			throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Retorna os estudantes de uma turma específica")
	@GetMapping("/{id}/students")
	public ResponseEntity<List<StudentDTO>> getStudentsByClassId(@PathVariable("id") Long id) {
		try{
			if(id == null) throw new ResponseException("Turma é obrigatória", HttpStatus.BAD_REQUEST);

			List<StudentDTO> students = this.studentService.getAllByClassId(id);
			return ResponseEntity.ok(students);

		} catch (Exception e) {
			throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Cadastra uma turma")
	@PostMapping
	public ResponseEntity<ClassDTO> save(@RequestBody ClassDTO dto, HttpServletRequest request) {
		try {
			if(dto == null) throw new ResponseException("Turma é obrigatória", HttpStatus.BAD_REQUEST);

			Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
			dto.setTeacherId(teacherId);

			ClassDTO classDTO = this.classService.saveOrUpdate(dto);

			return ResponseEntity.status(HttpStatus.CREATED).body(classDTO);
		} catch (Exception e) {
			throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Atualiza uma turma")
	@PutMapping
	public ResponseEntity<ClassDTO> update(@RequestBody ClassDTO dto, HttpServletRequest request) {

		try {
			if(dto == null) throw new ResponseException("Turma é obrigatória", HttpStatus.BAD_REQUEST);
			else if (dto.getId() == null) throw new ResponseException("Turma é obrigatória", HttpStatus.BAD_REQUEST);

			boolean exists = this.classService.exists(dto.getId());
			if (!exists) throw new ResponseException("Turma não encontrada", HttpStatus.NOT_FOUND);

			Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
			dto.setTeacherId(teacherId);

			ClassDTO classSaved = this.classService.saveOrUpdate(dto);
			return ResponseEntity.ok(classSaved);

		} catch (Exception e) {
			throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Deleta uma turma")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			boolean exists = this.classService.exists(id);
			if (!exists) throw new ResponseException("Turma não encontrada", HttpStatus.NOT_FOUND);

			this.classService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		} catch (Exception e) {
			throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Cadastra uma lista de estudantes em uma turma")
	@PostMapping("/{id}/students")
	public ResponseEntity saveStudentsForClass(@PathVariable("id") Long id, @RequestBody StudentWrapper wrapper) {
		try {
			if(id == null) throw new ResponseException("Turma é obrigatório", HttpStatus.BAD_REQUEST);
			if(wrapper == null) throw new ResponseException("Pelo menos um estudante é obrigatório", HttpStatus.BAD_REQUEST);

			this.classService.addStudentsIntoClass(wrapper.getStudentDTOS(), id);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);

		} catch (Exception e) {
			throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation("Desvincula aluno de uma turma")
	@DeleteMapping("{classId}/students/{studentId}")
	public ResponseEntity deleteStudentFromClass(@PathVariable("classId") Long classId, @PathVariable("studentId") Long studentId) {
		try {
			if (studentId == null && classId == null) throw new ResponseException("Turma e Estudante são obrigatorios", HttpStatus.BAD_REQUEST);

			this.classService.deleteStudentFromClass(studentId, classId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch (Exception e) {
			throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
