package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.StudentGrades;
import com.gladguys.alucoapi.entities.StudentWrapper;
import com.gladguys.alucoapi.entities.dto.ClassDTO;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.exception.notfound.ClassNotFoundException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.ClassService;
import com.gladguys.alucoapi.services.ExamGradeService;
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
	private ExamGradeService examGradeService;
	private JwtTokenUtil jwtTokenUtil;

	public ClassController(ClassService classService, StudentService studentService, ExamGradeService examGradeService, JwtTokenUtil jwtTokenUtil) {
		this.examGradeService = examGradeService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.classService = classService;
		this.studentService = studentService;
	}

	@ApiOperation(value = "Retorna as turmas de um professor específico")
	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<List<ClassDTO>> getAllByTeacher(@PathVariable("teacherId") Long teacherId) {
		if (teacherId == null) throw new ApiResponseException("Professor é obrigatório");

		List<ClassDTO> classes = this.classService.getAllByTeacher(teacherId);
		return ResponseEntity.ok(classes);
	}

	@ApiOperation(value = "Retorna as turmas do professor logado")
	@GetMapping
	public ResponseEntity<List<ClassDTO>> getAll(HttpServletRequest request) {
		Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
		List<ClassDTO> classes = this.classService.getAllByTeacher(teacherId);

		return ResponseEntity.ok(classes);
	}

	@ApiOperation(value = "Retorna uma turma pelo código")
	@GetMapping("/{id}")
	public ResponseEntity<ClassDTO> getById(@PathVariable("id") Long id) {
		if (id == null) throw new ApiResponseException("Turma é obrigatória");

		ClassDTO classFound = this.classService.getById(id);
		return ResponseEntity.ok(classFound);
	}

	@ApiOperation(value = "Retorna os estudantes de uma turma específica")
	@GetMapping("/{id}/students")
	public ResponseEntity<List<StudentDTO>> getStudentsByClassId(@PathVariable("id") Long id) {
		if (id == null) throw new ApiResponseException("Turma é obrigatória");

		List<StudentDTO> students = this.studentService.getAllByClassId(id);
		return ResponseEntity.ok(students);
	}

	@ApiOperation(value = "Cadastra uma turma")
	@PostMapping
	public ResponseEntity<ClassDTO> save(@RequestBody ClassDTO dto, HttpServletRequest request) {
		validateClassData(dto);
		dto.setTeacherId(
				jwtTokenUtil.getTeacherIdFromToken(request).longValue());

		return ResponseEntity
				.status(HttpStatus.CREATED).body(this.classService.saveOrUpdate(dto));
	}

	@ApiOperation(value = "Atualiza uma turma")
	@PutMapping
	public ResponseEntity<ClassDTO> update(@RequestBody ClassDTO dto, HttpServletRequest request) {
		validateClassData(dto);

		boolean exists = this.classService.exists(dto.getId());
		if (!exists) throw new ClassNotFoundException(dto.getId());

		dto.setTeacherId(
				jwtTokenUtil.getTeacherIdFromToken(request).longValue());

		return ResponseEntity.ok(this.classService.saveOrUpdate(dto));
	}

	@ApiOperation(value = "Deleta uma turma")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		boolean exists = this.classService.exists(id);
		if (!exists) throw new ClassNotFoundException(id);

		this.classService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@ApiOperation(value = "Cadastra uma lista de estudantes em uma turma")
	@PostMapping("/{id}/students")
	public ResponseEntity saveStudentsForClass(@PathVariable("id") Long id, @RequestBody StudentWrapper wrapper) {
		if (id == null) throw new ApiResponseException("Turma é obrigatória");
		if (wrapper == null) throw new ApiResponseException("Aluno(s) obrigatório(s)");

		this.classService.addStudentsIntoClass(wrapper.getStudentDTOS(), id);
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@ApiOperation("Desvincula aluno de uma turma")
	@DeleteMapping("{classId}/students/{studentId}")
	public ResponseEntity deleteStudentFromClass(@PathVariable("classId") Long classId, @PathVariable("studentId") Long studentId) {
		if (studentId == null) throw new ApiResponseException("Aluno é obrigatório");
		if (classId == null) throw new ApiResponseException("Turma é obrigatória");

		this.classService.deleteStudentFromClass(studentId, classId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@ApiOperation("Retorna quadro de notas da turma")
	@GetMapping("/{id}/grades")
	public ResponseEntity<List<StudentGrades>> getGradesBoard(@PathVariable("id") Long id) {
		List<StudentGrades> gradeBoardFromClass = this.examGradeService.getGradeBoardFromClass(id);
		return ResponseEntity.ok(gradeBoardFromClass);
	}

	private void validateClassData(@RequestBody ClassDTO dto) {
		if (dto == null) throw new ApiResponseException("Turma é obrigatória");
		if (dto.getClassName().replaceAll("\\s+","").length() == 0)
			throw new ApiResponseException("Nome para turma inválido");
	}
}
