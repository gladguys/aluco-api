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
import java.util.Set;

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
		try {
			Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
			ExamFilter examFilter = new ExamFilter(name, classId, teacherId);

			return ResponseEntity.ok(this.examService.getAllByFilterClassOrTeacher(examFilter));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Busca detalhe de um exame específico")
	public ResponseEntity<ExamDTO> getById(HttpServletRequest request, @PathVariable("id") Long id) {
		try {
			Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
            ExamDTO dto = this.examService.getById(id);

            if(dto.getTeacherId() != teacherId) return ResponseEntity.

            return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping
	@ApiOperation(value = "Cadastra uma exame")
	public ResponseEntity<ExamDTO> save(@RequestBody ExamDTO examDTO) {
		try {
			Exam exam = this.examService.saveOrUpdate(examDTO);
			return ResponseEntity.ok(exam.toDTO());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping
	@ApiOperation(value = "Atualiza uma prova com base no objeto passado no body da request")
	public ResponseEntity<ExamDTO> update(@RequestBody ExamDTO examDTO) {
		try {
			if (examDTO == null || examDTO.getId() == null)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

			boolean exists = this.examService.exists(examDTO.getId());
			if (!exists) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

			Exam exam = this.examService.saveOrUpdate(examDTO);
			return ResponseEntity.ok(exam.toDTO());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta a prova pelo id informado na endpoint")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		try {
			boolean exists = this.examService.exists(id);
			if (!exists) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

			this.examService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
