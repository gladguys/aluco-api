package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Exam;
import com.gladguys.alucoapi.entities.dto.ExamDTO;
import com.gladguys.alucoapi.services.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private ExamService examService;

    ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<ExamDTO>> getAllByTeacher(@PathVariable("teacherId") Long teacherId) {
        try {
            List<ExamDTO> exams = this.examService.getAllByTeacherId(teacherId);
            return ResponseEntity.ok(exams);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<ExamDTO> save(@RequestBody ExamDTO examDTO) {
        try {
            Exam exam = this.examService.saveOrUpdate(examDTO);
            return ResponseEntity.ok(exam.toDTO());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}