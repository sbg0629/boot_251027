package com.boot.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServicelmpl implements EmailService  {

	
	private final JavaMailSender mailSender;
	
	public EmailServicelmpl(JavaMailSender mailSender) {
		this.mailSender=mailSender;
	}
	
	
	@Override
	public void sendEmail(String to, String subject, String message) {
		log.info("@# sendEmail()");
		
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("sonjy123036@gmail.com");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		log.info("@#simpleMailMessage => " + simpleMailMessage);
		
		this.mailSender.send(simpleMailMessage);

	}
	
}
