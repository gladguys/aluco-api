package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.GradesWrapper;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.dto.ExamGradeDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.exception.notfound.ExamNotFoundException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.ExamGradeService;
import com.gladguys.alucoapi.services.ExamService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

	private JwtTokenUtil jwtTokenUtil;
	private ExamService examService;
	private ExamGradeService gradeService;

	ExamController(JwtTokenUtil jwtTokenUtil, ExamService examService, ExamGradeService gradeService) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.examService = examService;
		this.gradeService = gradeService;
	}

	@GetMapping
	@ApiOperation(value = "Retorna as provas de um professor específico")
	public ResponseEntity<List<ExamDTO>> get(HttpServletRequest request,
	                                         @RequestParam(value = "name", required = false) String name,
	                                         @RequestParam(value = "classId", required = false) Long classId) {

		Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
		ExamFilter examFilter = new ExamFilter(name, classId, teacherId);

		return ResponseEntity.ok(this.examService.getAllByFilterClassOrTeacher(examFilter));
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Busca detalhes de uma prova específica")
	public ResponseEntity<ExamDTO> getById(HttpServletRequest request, @PathVariable("id") Long id) {
		Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
		ExamDTO dto = this.examService.getById(id);

		if (!dto.getTeacherId().equals(teacherId)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		return ResponseEntity.ok(dto);
	}


	@PostMapping
	@ApiOperation(value = "Cadastra uma prova")
	public ResponseEntity<ExamDTO> save(@RequestBody ExamDTO examDTO) {
		Exam exam = this.examService.saveOrUpdate(examDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(exam.toDTO());
	}

	@PutMapping
	@ApiOperation(value = "Atualiza uma prova com base no objeto passado no body da request")
	public ResponseEntity<ExamDTO> update(@RequestBody ExamDTO examDTO) {
		if (examDTO == null || examDTO.getId() == null) throw new ApiResponseException("Prova é obrigatória");

		boolean exists = this.examService.exists(examDTO.getId());
		if (!exists) throw new ExamNotFoundException(examDTO.getId());

		Exam exam = this.examService.saveOrUpdate(examDTO);
		return ResponseEntity.ok(exam.toDTO());
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta a prova pelo id informado na endpoint")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		if (id == null) throw new ApiResponseException("Prova é obrigatória");

		boolean exists = this.examService.exists(id);
		if (!exists) throw new ExamNotFoundException(id);

		this.examService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}

	@PostMapping(value = "/{id}/grades")
	@ApiOperation(value = "Salva notas de alunos para um específico exame")
	public ResponseEntity<String> saveGrades(@RequestBody GradesWrapper gradesDTO, @PathVariable("id") Long id) {

		try{
			this.gradeService.saveAllGrades(gradesDTO.getGrades());
			return ResponseEntity.ok("notas salvas com sucesso");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping(value = "/{id}/grades")
	@ApiOperation(value = "Resgatar todas as notas dos alunos daquele exame")
	public ResponseEntity<List<ExamGradeDTO>> getGradesByExam(@PathVariable("id") Long id) {

		return ResponseEntity.ok(this.gradeService.getGradesByExamId(id));

	}

	@DeleteMapping(value = "/{id}/grades")
	@ApiOperation(value = "Remove registro de nota do aluno para aquele exame")
	public ResponseEntity delete(@RequestBody ExamGradeDTO dto) {
		dto.setGrade(null);
		this.gradeService.deleteGrade(dto);

		return ResponseEntity.status(HttpStatus.OK).body("Nota removida com sucesso");

	}

}
