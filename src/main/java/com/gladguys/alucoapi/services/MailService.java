package com.gladguys.alucoapi.services;

import com.gladguys.alucoapi.entities.dto.CallDTO;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import java.util.List;

public interface MailService {

	void sendEmail(List<CallDTO> callsForDay, String email) throws MessagingException;
}
