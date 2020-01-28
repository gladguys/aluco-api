package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.component.ReportGenerate;
import com.gladguys.alucoapi.entities.dto.CallDTO;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.security.jwt.JwtTokenUtil;
import com.gladguys.alucoapi.services.CallService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ReportGenerate reportGenerate;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CallService callService;

    public ReportController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @ApiOperation(value = "Gerar relatório diário de alunos ausentes")
    @PostMapping("/dailyabsence/class/{classId}")
    public ResponseEntity<byte[]> dailyAbsenceStudents(HttpServletRequest request, HttpServletResponse response, @PathVariable Long classId) {
        if (classId == null) throw new ApiResponseException("Turma é obrigatória");

        Long teacherId = jwtTokenUtil.getTeacherIdFromToken(request).longValue();
        List<CallDTO> callsForDay = this.callService.getCallsForDailyReport(classId);
        sendEmail(callsForDay);

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

    void sendEmail(List<CallDTO> callsForDay) {

        final String username = "alucoapp@gmail.com";
        final String password = "aluco@123";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("dineresc@gmail.com")
            );
            message.setSubject("Chamada de Prof."+ callsForDay.get(0).getTeacherName() +" para dia " + new Date().toString());

            StringBuilder msg = new StringBuilder();
            msg.append(" Olá, ");
            msg.append(" segue abaixo a lista de chamadas para o dia ").append(new Date().toString()).append("\n");
            callsForDay.stream().forEach(callDTO -> {
                msg.append(callDTO.getRegistrationNumber() + " - " + callDTO.getStudentName() + ": " + callDTO.getStatus() + "\n");
            });
            message.setText(msg.toString());
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
