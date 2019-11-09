package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.services.CallService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
	public ResponseEntity<Set<Call>> getAll(@PathVariable("classId") Long classId, @RequestParam("date") Date date) {
		if (classId == null) throw new ApiResponseException("Turma é obrigatório");

		Set<Call> calls = this.callService.getAllByClassAndDate(classId, date);
		return ResponseEntity.ok(calls);
	}

	@ApiOperation(value = "Cadastra uma lista de chamadas")
	@PostMapping
	public ResponseEntity saveAll(Set<Call> calls) {
		return ResponseEntity.ok("Calls saved with success");
	}

	@ApiOperation(value = "Atualiza uma chamada")
	@PutMapping
	public ResponseEntity<Call> update(Call call) {
		Call callSaved = this.callService.update(call);

		return ResponseEntity.ok(callSaved);
	}

	@ApiOperation(value = "Retorna as chamadas de um estudante específico")
	@GetMapping("/student/{studentId}")
	public ResponseEntity<Set<Call>> callsForStudent(@PathVariable("studentId") Long studentId) {
		Set<Call> calls = this.callService.getAllByStudent(studentId);

		return ResponseEntity.ok(calls);
	}

}
