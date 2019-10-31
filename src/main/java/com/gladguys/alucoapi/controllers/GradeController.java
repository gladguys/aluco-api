package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Grade;
import com.gladguys.alucoapi.entities.dto.GradeDTO;
import com.gladguys.alucoapi.services.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<GradeDTO>> allGradesForExam(@PathVariable("examId") Long examId) {

        try {
            List<GradeDTO> grades = this.gradeService.getAllGradesByExam(examId);
            return ResponseEntity.ok(grades);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeDTO>> allGradesForStudent(@PathVariable("studentId") Long studentId) {

        try {
            List<GradeDTO> grades = this.gradeService.getAllGradesByStudent(studentId);
            return ResponseEntity.ok(grades);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<List<GradeDTO>> allGradesForClass(@PathVariable("classId") Long classId) {

        try {
            List<GradeDTO> grades = this.gradeService.getAllGradesByClass(classId);
            return ResponseEntity.ok(grades);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Grade> save(@RequestBody GradeDTO dto) {

        try {
            //TODO: create a custom exception to know when creating a grade for a existing grade for student-class
            return ResponseEntity.ok(this.gradeService.saveOrUpdate(dto));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Grade> update(@RequestBody GradeDTO dto) {

        try {

            Grade grade = this.gradeService.saveOrUpdate(dto);
            return ResponseEntity.ok(grade);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            this.gradeService.deleteById(id);
            return ResponseEntity.ok(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
