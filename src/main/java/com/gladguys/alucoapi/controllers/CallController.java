package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Call;
import com.gladguys.alucoapi.exception.ResponseException;
import com.gladguys.alucoapi.services.CallService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        try {
            Set<Call> calls = this.callService.getAllByClassAndDate(classId, date);
            return ResponseEntity.ok(calls);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Cadastra uma lista de chamadas")
    @PostMapping
    public ResponseEntity saveAll(Set<Call> calls) {
        try {
            return ResponseEntity.ok("Calls saved with success");

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualiza uma chamada")
    @PutMapping
    public ResponseEntity<Call> update(Call call) {
        try {
            Call callSaved = this.callService.update(call);
            return ResponseEntity.ok(callSaved);
        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Retorna as chamadas de um estudante específico")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Set<Call>> callsForStudent(@PathVariable("studentId") Long studentId) {
        try {
            Set<Call> calls = this.callService.getAllByStudent(studentId);
            return ResponseEntity.ok(calls);

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
