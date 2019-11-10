package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.entities.filters.ExamFilter;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
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

	ExamController(JwtTokenUtil jwtTokenUtil, ExamService examService) {
		this.jwtTokenUtil = jwtTokenUtil;
		this.examService = examService;
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

		if (dto.getTeacherId() != teacherId) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

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
			boolean exists = this.examService.exists(id);
			if (!exists) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

			this.examService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

	}

}
