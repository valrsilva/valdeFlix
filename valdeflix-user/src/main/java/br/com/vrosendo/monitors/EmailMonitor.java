package br.com.vrosendo.monitors;

import br.com.vrosendo.events.UserEvent;
import br.com.vrosendo.services.EmailService;
import br.com.vrosendo.services.UserService;

public class EmailMonitor {

	public final EmailService emailService;
	
	public EmailMonitor(EmailService emailService, UserService userService){
		this.emailService = emailService;
		userService.subscribeToUserEvents(this::handleUserEvents);
	}
	
	private void handleUserEvents(UserEvent event){
		System.out.println("HANDLING USER EVENT...");
		emailService.sendEmail(event.getEmailAddress(), "from@gmail.com.br", "subject test", "content test");
	}
	
}
