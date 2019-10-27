package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        try {
            List<Student> students = this.studentService.findAll();
            return ResponseEntity.ok(students);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/teacher")
    public ResponseEntity<Set<Student>> listAllByTeacher(HttpServletRequest request) {
        try {
            Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
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
            return ResponseEntity.status(HttpStatus.CREATED).body(studentSaved);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Student> update(@RequestBody StudentDTO studentDTO) {
        try {
            if(studentDTO == null) throw new Exception();
            else if (studentDTO.getId() == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

            Student studentUpdated = this.studentService.update(studentDTO.toEntity());
            return ResponseEntity.ok(studentUpdated);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> delete(@PathVariable Long studentId) {
        try {
            Student student = this.studentService.getById(studentId);
            if (student == null) throw new Exception();

            this.studentService.deleteById(student.getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
