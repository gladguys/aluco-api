package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.dto.LessonPlanDTO;
import com.gladguys.alucoapi.entities.filters.LessonPlanFilter;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.ClassService;
import com.gladguys.alucoapi.services.LessonPlanService;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(value = "/api/lessons")
public class LessonPlanController {

	private LessonPlanService lessonPlanService;
	private ClassService classService;
	private JwtTokenUtil jwtTokenUtil;


	public LessonPlanController(LessonPlanService lessonPlanService, ClassService classService, JwtTokenUtil jwtTokenUtil) {
		this.lessonPlanService = lessonPlanService;
		this.classService = classService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@GetMapping
	@ApiOperation("busca todos os planos de aula por turma com filtros para classId e localDate")
	public ResponseEntity<List<LessonPlanDTO>> getAll(HttpServletRequest request,
													  @RequestParam(value = "classId", required = false) Long classId,
													  @RequestParam(value = "lessonDate", required = false) String lessonDateStr) {
		Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
		LocalDate lessonDate = null;

		if (classId != null && !this.classService.isClassFromTeacher(classId, teacherId))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		if(lessonDateStr != null) {
			DateTimeFormatter ddMMyyyyFormarter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			lessonDate = LocalDate.parse(lessonDateStr, ddMMyyyyFormarter);
		}

		LessonPlanFilter filter = new LessonPlanFilter(classId,lessonDate);

		return ResponseEntity.ok(this.lessonPlanService.getByFilters(filter));
	}

	@GetMapping(value = "/{id}")
	@ApiOperation("get plano de aula por id")
	public ResponseEntity<LessonPlanDTO> get(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.lessonPlanService.getById(id));

	}

	@PostMapping
	@ApiOperation("salva plano de aula")
	public ResponseEntity<LessonPlanDTO> save(@RequestBody LessonPlanDTO dto) {
		return ResponseEntity.ok(this.lessonPlanService.save(dto));
	}

	@PutMapping
	@ApiOperation("Editar plano de aula")
	public ResponseEntity<LessonPlanDTO> update(@RequestBody LessonPlanDTO dto) {
		if (dto == null || dto.getId() == null)  throw new ApiResponseException("Plano de Aula ou seu id não informado para editar");
		return ResponseEntity.ok(this.lessonPlanService.save(dto));
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation("deleta plano de aula por id")
	public ResponseEntity<String> delete(@PathVariable("id") Long id ) {
		this.lessonPlanService.delete(id);
		return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
