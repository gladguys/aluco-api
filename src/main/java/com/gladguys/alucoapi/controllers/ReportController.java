package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.component.ReportGenerate;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.exception.internalservererror.ReportInternalServerErrorException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ReportGenerate reportGenerate;

    @Autowired
    private DataSource dataSource;

    public ReportController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @ApiOperation(value = "Gerar relatório diário de alunos ausentes")
    @PostMapping("/dailyabsence/class/{classId}")
    public ResponseEntity<byte[]> dailyAbsenceStudents(HttpServletRequest request, HttpServletResponse response, @PathVariable Long classId) {
        if (classId == null) throw new ApiResponseException("Turma é obrigatória");

        Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();

        try {
            reportGenerate.addParameter("class_id", classId);
            reportGenerate.addParameter("teacher_id", teacherId);
            byte[] report = reportGenerate.generate("rel_alunos_ausentes_por_dia.jasper");
            return reportGenerate.exportPDF(report, "rel_alunos_ausentes_por_dia");
        } catch (SQLException | JRException exception) {
            throw new ReportInternalServerErrorException("Alunos ausentes por dia");
        }
    }
}
