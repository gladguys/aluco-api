package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.services.StudentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Set<Student>> listAllByTeacher(@PathVariable("teacherId") Long teacherId) {
        try {
            Set<Student> students = this.studentService.getAllByTeacher(teacherId);
            return ResponseEntity.ok(students);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody StudentDTO dto) {
        try {
            if(dto == null) throw new Exception();

            Student studentSaved = this.studentService.save(dto.toEntity());
            return ResponseEntity.ok(studentSaved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
