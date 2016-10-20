package br.com.vrosendo.events;

public class UserEventCreate extends UserEvent {

	public UserEventCreate(String userName, String emailAddress){
		super(userName, emailAddress);
	}
	
}
