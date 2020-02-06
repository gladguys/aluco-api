package com.gladguys.alucoapi.services.impl;

import com.gladguys.alucoapi.entities.dto.CallDTO;
import com.gladguys.alucoapi.entities.enums.StatusEnum;
import com.gladguys.alucoapi.services.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

	@Value( "${alucoapp.email}" )
	private String alucoEmail;

	@Value( "${alucoapp.email.pwd}" )
	private String alucoEmailPwd;

	@Value( "${mail.smtp.host}" )
	private String smtpHost;

	@Value( "${mail.smtp.port}" )
	private String smtpPort;

	@Value( "${mail.smtp.auth}" )
	private String smtpAuth;

	@Value( "${mail.smtp.starttls.enable}" )
	private String smtpTLS;

	@Override
	public void sendEmail(List<CallDTO> callsForDay, String email) throws MessagingException {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", smtpHost);
		prop.put("mail.smtp.port", smtpPort);
		prop.put("mail.smtp.auth", smtpAuth);
		prop.put("mail.smtp.starttls.enable", smtpTLS);

		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(alucoEmail, alucoEmailPwd);
					}
				});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("from@gmail.com"));
		message.setRecipients(
				Message.RecipientType.TO,
				InternetAddress.parse(email)
		);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		message.setSubject("Chamada de Prof. " + callsForDay.get(0).getTeacherName() + " para dia " + dateFormat.format(date));

		StringBuilder msg = new StringBuilder();
		msg.append(" Olá, ");
		msg.append(" segue abaixo a lista de chamadas feita para o dia ").append(dateFormat.format(date)).append("\n");
		msg.append("Professor: " + callsForDay.get(0).getTeacherName()).append("\n");
		msg.append("Turma: " + callsForDay.get(0).getClassName()).append("\n\n");

		callsForDay.stream().filter(filter -> filter.getStatus().equals(StatusEnum.FALTA) || filter.getStatus().equals(StatusEnum.FALTA_JUSTIFICADA))
				.forEach(callDTO -> msg.append(callDTO.getRegistrationNumber() + " - " + callDTO.getStudentName() + ": " + callDTO.getStatus() + "\n"));
		message.setText(msg.toString());
		Transport.send(message);
	}

}
