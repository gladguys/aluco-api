package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.StudentGrades;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.exception.notfound.StudentNotFoundException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.ExamGradeService;
import com.gladguys.alucoapi.services.StudentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private StudentService studentService;
	private ExamGradeService examGradeService;

	public StudentController(StudentService studentService, JwtTokenUtil jwtTokenUtil, ExamGradeService examGradeService) {
		this.studentService = studentService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.examGradeService = examGradeService;
	}

	@ApiOperation(value = "Retorna estudante por id")
	@GetMapping
	public ResponseEntity<List<StudentDTO>> getAll(HttpServletRequest request) {
		Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
		List<StudentDTO> students = this.studentService.getAllByTeacher(teacherId);

		return ResponseEntity.ok(students);
	}

	@ApiOperation(value = "Retorna os estudantes do professor logado")
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> get(HttpServletRequest request, @PathVariable("id") Long id) {
		Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();

		StudentDTO student = this.studentService.getById(id);

		return ResponseEntity.ok(student);
	}

	@ApiOperation(value = "Cadastra um estudante")
	@PostMapping
	public ResponseEntity<Student> save(HttpServletRequest request, @RequestBody StudentDTO dto) {
		if (dto == null) throw new ApiResponseException("Aluno é obrigatório");

		Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
		dto.setTeacherId(teacherId);

		Student studentSaved = this.studentService.save(dto.toEntity());
		return ResponseEntity.status(HttpStatus.CREATED).body(studentSaved);
	}

	@ApiOperation(value = "Atualiza um estudante")
	@PutMapping
	public ResponseEntity<Student> update(HttpServletRequest request, @RequestBody StudentDTO studentDTO) {
		if (studentDTO == null || studentDTO.getId() == null) throw new ApiResponseException("Aluno é obrigatório");

		boolean exists = this.studentService.existsById(studentDTO.getId());
		if (!exists) throw new StudentNotFoundException(studentDTO.getId());

		Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
		studentDTO.setTeacherId(teacherId);

		Student studentUpdated = this.studentService.update(studentDTO.toEntity());
		return ResponseEntity.ok(studentUpdated);
	}

	@ApiOperation(value = "Deleta um estudante")
	@DeleteMapping("/{studentId}")
	public ResponseEntity<Student> delete(@PathVariable Long studentId) {
		if (studentId == null) throw new ApiResponseException("Aluno é obrigatório");

		boolean exists = this.studentService.existsById(studentId);
		if (!exists) throw new StudentNotFoundException(studentId);

		this.studentService.deleteById(studentId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@ApiOperation(value = "Retorna todos os resultados de provas por aluno")
	@GetMapping("/{id}/grades")
	public ResponseEntity<List<StudentGrades>> getAllExamGradesFromStudent(HttpServletRequest request,
																		   @PathVariable("id") Long studentId,
																		   @RequestParam(required = false) Long classId) {

		Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
		if (teacherId == null) throw new ApiResponseException("Não encontrado identificacao do professor na requisição");

		List<StudentGrades> studentGrades = this.examGradeService.getGradeBoardFromClass(classId, studentId);

		return ResponseEntity.ok(studentGrades);
	}



}
