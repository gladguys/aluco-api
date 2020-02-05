package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.component.ReportGenerate;
import com.gladguys.alucoapi.entities.dto.CallDTO;
import com.gladguys.alucoapi.entities.enums.StatusEnum;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.CallService;
import com.gladguys.alucoapi.services.MailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private final JwtTokenUtil jwtTokenUtil;

    private final ReportGenerate reportGenerate;

    private final DataSource dataSource;

    private final CallService callService;

    private final MailService mailService;

    public ReportController(JwtTokenUtil jwtTokenUtil, ReportGenerate reportGenerate, DataSource dataSource, CallService callService, MailService mailService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.reportGenerate = reportGenerate;
        this.dataSource = dataSource;
        this.callService = callService;
        this.mailService = mailService;
    }

    @ApiOperation(value = "Gerar relatório diário de alunos ausentes")
    @GetMapping("/dailyabsence/class/{classId}")
    public ResponseEntity<byte[]> dailyAbsenceStudents(HttpServletRequest request,
                                                       @PathVariable Long classId,
                                                       @RequestParam("email") String email) throws MessagingException {
        if (classId == null) throw new ApiResponseException("Turma é obrigatória");

        List<CallDTO> callsForDay = this.callService.getCallsForDailyReport(classId);
        this.mailService.sendEmail(callsForDay, email);

        return null;

       /*
       * try {
            reportGenerate.addParameter("class_id", classId);
            reportGenerate.addParameter("teacher_id", teacherId);
            byte[] report = reportGenerate.generate("rel_alunos_ausentes_por_dia.jasper");
            return reportGenerate.exportPDF(report, "rel_alunos_ausentes_por_dia");
        } catch (SQLException | JRException exception) {
            throw new ReportInternalServerErrorException("Alunos ausentes por dia");
        }
       * */
    }

}
