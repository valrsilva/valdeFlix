package br.com.vrosendo.services;

public interface EmailService {
	
	void sendEmail(String to, String from, String subject, String text);
	
}
