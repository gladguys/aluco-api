package com.gladguys.alucoapi.controllers;

import java.util.List;

import com.gladguys.alucoapi.entities.StudentClassObservation;
import com.gladguys.alucoapi.entities.dto.StudentClassObservationDTO;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.StudentClassObservationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/observations/student")
public class StudentClassObservationController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private StudentClassObservationService studentClassObservationService;

    public StudentClassObservationController(StudentClassObservationService studentClassObservationService, JwtTokenUtil jwtTokenUtil) {
        this.studentClassObservationService = studentClassObservationService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @ApiOperation(value = "Salva as observações realizadas pelo professor em sala de aula de um determinado estudante")
    @PostMapping
    public ResponseEntity<StudentClassObservation> save(HttpServletRequest request, StudentClassObservationDTO studentClassObservationDTO) {
        Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
        StudentClassObservation studentClassObservation = this.studentClassObservationService.save(studentClassObservationDTO.toEntity());

        return ResponseEntity.ok(studentClassObservation);
    }

    @ApiOperation(value = "Retorna as observações realizadas pelo professor em sala de aula de um estudante especifico a partir do seu id")
    @GetMapping("{studentId}")
    public ResponseEntity<List<StudentClassObservationDTO>> get(HttpServletRequest request, @PathVariable("studentId") Long studentId) {
        Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
        List<StudentClassObservationDTO> studentClassObservationDTOList = this.studentClassObservationService.getStudentObservation(studentId);

        return ResponseEntity.ok(studentClassObservationDTOList);
    }
}
