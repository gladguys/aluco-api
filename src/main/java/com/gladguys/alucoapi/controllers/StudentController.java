package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.entities.Student;
import com.gladguys.alucoapi.entities.dto.StudentDTO;
import com.gladguys.alucoapi.exception.ResponseException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.StudentService;
import io.swagger.annotations.ApiOperation;
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

import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private StudentService studentService;

    public StudentController(StudentService studentService, JwtTokenUtil jwtTokenUtil) {
        this.studentService = studentService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @ApiOperation(value = "Retorna os estudantes do professor logado")
    @GetMapping
    public ResponseEntity<Set<Student>> getAll(HttpServletRequest request) {
        try {
            Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
            Set<Student> students = this.studentService.getAllByTeacher(teacherId);
            return ResponseEntity.ok(students);

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Cadastra um estudante")
    @PostMapping
    public ResponseEntity<Student> save(HttpServletRequest request, @RequestBody StudentDTO dto) {
        try {
            if(dto == null) throw new ResponseException("Estudante é obrigatório", HttpStatus.BAD_REQUEST);

            Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
            dto.setTeacherId(teacherId);

            Student studentSaved = this.studentService.save(dto.toEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(studentSaved);

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Atualiza um estudante")
    @PutMapping
    public ResponseEntity<Student> update(HttpServletRequest request, @RequestBody StudentDTO studentDTO) {
        try {
            if(studentDTO == null) throw new ResponseException("Estudante é obrigatorio", HttpStatus.BAD_REQUEST);
            else if (studentDTO.getId() == null) throw new ResponseException("Estudante é obrigatório", HttpStatus.BAD_REQUEST);

            boolean exists = this.studentService.existsById(studentDTO.getId());
            if (!exists) throw new ResponseException("Estudante não encontrado", HttpStatus.NOT_FOUND);

            Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
            studentDTO.setTeacherId(teacherId);

            Student studentUpdated = this.studentService.update(studentDTO.toEntity());
            return ResponseEntity.ok(studentUpdated);

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Deleta um estudante")
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Student> delete(@PathVariable Long studentId) {
        try {
            boolean exists = this.studentService.existsById(studentId);
            if (!exists) throw new ResponseException("Estudante não encontrado", HttpStatus.NOT_FOUND);

            this.studentService.deleteById(studentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

        } catch (Exception e) {
            throw new ResponseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
