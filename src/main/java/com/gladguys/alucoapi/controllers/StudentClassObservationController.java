package com.gladguys.alucoapi.controllers;

import java.util.List;

import com.gladguys.alucoapi.entities.StudentClassObservation;
import com.gladguys.alucoapi.entities.dto.StudentClassObservationDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.exception.notfound.StudentClassObservationNotFoundException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.StudentClassObservationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<StudentClassObservationDTO> save(HttpServletRequest request, @RequestBody StudentClassObservationDTO studentClassObservationDTO) {
        Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
        StudentClassObservation studentClassObservation = this.studentClassObservationService.save(studentClassObservationDTO.toEntity());

        return ResponseEntity.ok(studentClassObservation.toDTO());
    }

    @ApiOperation(value = "Atualizando as observações realizadas pelo professor em sala de aula de um determinado estudante")
    @PutMapping
    public ResponseEntity<StudentClassObservationDTO> update(@RequestBody StudentClassObservationDTO studentClassObservationDTO) {
        if (studentClassObservationDTO == null || studentClassObservationDTO.getId() == null) throw new ApiResponseException("Observação sobre o aluno é obrigatório");

        boolean exists = this.studentClassObservationService.existsById(studentClassObservationDTO.getId());
        if (!exists) throw new StudentClassObservationNotFoundException(studentClassObservationDTO.getId());

        StudentClassObservation studentClassObservationUpdated = this.studentClassObservationService.update(studentClassObservationDTO.toEntity());
        return ResponseEntity.ok(studentClassObservationUpdated.toDTO());
    }

    @ApiOperation(value = "Deleta a observação do estudante")
    @DeleteMapping("{idStudentClassObservation}")
    public ResponseEntity<StudentClassObservationDTO> delete(@PathVariable Long idStudentClassObservation) {
        if (idStudentClassObservation == null) throw new ApiResponseException("Observação sobre o aluno é obrigatório");

        boolean exists = this.studentClassObservationService.existsById(idStudentClassObservation);
        if (!exists) throw new StudentClassObservationNotFoundException(idStudentClassObservation);

        this.studentClassObservationService.deleteById(idStudentClassObservation);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @ApiOperation(value = "Retorna as observações realizadas pelo professor em sala de aula de um estudante especifico a partir do seu id")
    @GetMapping("{studentId}")
    public ResponseEntity<List<StudentClassObservationDTO>> get(HttpServletRequest request, @PathVariable("studentId") Long studentId) {
        Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
        List<StudentClassObservationDTO> studentClassObservationDTOList = this.studentClassObservationService.getStudentObservation(studentId);

        return ResponseEntity.ok(studentClassObservationDTOList);
    }
}
