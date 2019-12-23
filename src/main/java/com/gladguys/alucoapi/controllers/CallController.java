package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.entities.dto.CallDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.services.CallService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/calls")
public class CallController {

	CallService callService;

	public CallController(CallService callService) {
		this.callService = callService;
	}

	@ApiOperation(value = "Retorna as chamadas de um determinado dia e turma")
	@GetMapping("/class/{classId}")
	public ResponseEntity<List<CallDTO>> getAll(@PathVariable("classId") Long classId, @RequestParam("date") String dateStr) {
		if (classId == null) throw new ApiResponseException("Turma é obrigatório");
		LocalDate date = null;

		if (dateStr != null) {
			DateTimeFormatter ddMMyyyyFormarter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			date = LocalDate.parse(dateStr, ddMMyyyyFormarter);
		}

		List<CallDTO> calls = this.callService.getAllByClassAndDate(classId, date);
		return ResponseEntity.ok(calls);
	}

	@ApiOperation(value = "Cadastra uma lista de chamadas")
	@PostMapping
	public ResponseEntity<CallDTO> save(@RequestBody CallDTO callDTO) throws Exception {
		return ResponseEntity.ok(this.callService.save(callDTO));
	}

	@ApiOperation(value = "Atualiza uma chamada")
	@PutMapping
	public ResponseEntity<CallDTO> update(@RequestBody CallDTO callDTO) throws Exception {
		return ResponseEntity.ok(this.callService.save(callDTO));
	}

	@ApiOperation(value = "Retorna as chamadas de um estudante específico")
	@GetMapping("/student/{studentId}")
	public ResponseEntity<Set<Call>> callsForStudent(@PathVariable("studentId") Long studentId) {
		Set<Call> calls = this.callService.getAllByStudent(studentId);

		return ResponseEntity.ok(calls);
	}

}
