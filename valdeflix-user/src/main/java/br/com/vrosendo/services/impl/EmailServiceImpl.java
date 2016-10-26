package br.com.vrosendo.services.impl;

import org.springframework.stereotype.Service;

import br.com.vrosendo.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Override
	public void sendEmail(String to, String from, String subject, String text) {
		
		System.out.println("SENDING CONFIRMATION EMAIL...");
		System.out.println("TO: " + to);
		System.out.println("FROM: " + from);
		System.out.println("SUBJECT: " + subject);
		System.out.println("TEXT: " + text);
		
	}

	
}
