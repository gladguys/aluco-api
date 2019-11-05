package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.entities.dto.GradeDTO;
import com.gladguys.alucoapi.exception.ResponseException;
import com.gladguys.alucoapi.services.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<Set<Grade>> allGradesForExam(@PathVariable("examId") Long examId) {

        try {
            Set<Grade> grades = this.gradeService.getAllGradesByExam(examId);
            return ResponseEntity.ok(grades);

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Set<Grade>> allGradesForStudent(@PathVariable("studentId") Long studentId) {

        try {
            Set<Grade> grades = this.gradeService.getAllGradesByExam(studentId);
            return ResponseEntity.ok(grades);

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Grade> save(@RequestBody GradeDTO dto) {

        try {

            Grade grade = this.gradeService.saveOrUpdate(dto);
            return ResponseEntity.ok(grade);

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Grade> update(@RequestBody GradeDTO dto) {

        try {

            Grade grade = this.gradeService.saveOrUpdate(dto);
            return ResponseEntity.ok(grade);

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            this.gradeService.deleteById(id);
            return ResponseEntity.ok(null);
        }catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
