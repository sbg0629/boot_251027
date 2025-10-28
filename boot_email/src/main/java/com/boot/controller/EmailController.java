package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.dto.EmailDTO;
import com.boot.service.EmailService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class EmailController {
	
	@Autowired
	private EmailService service;
	
	@RequestMapping("/send_view")
	public String send_view() {
		log.info("@#sned_view()");
		
		return"send_view";
	}
	
	@PostMapping("/send_email")
	public String send_email(EmailDTO dto) {
		log.info("@#send_email()");
		log.info("@# dto => "+dto);
		
		service.sendEmail(dto.getTo(), dto.getSubject(), dto.getMessage());
		
		return"send_ok";
	}
	
	
	
}
