package br.com.vrosendo.events;

import java.util.Date;

public class UserEvent {

	private final String userName;
	private final String emailAddress;
	private final Date eventDate;
	
	public UserEvent(String userName, String emailAddress) {
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.eventDate = new Date();
	}
	
	public String getUserName() {
		return userName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public Date getEventDate() {
		return eventDate;
	}
	
}
