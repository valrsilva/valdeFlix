package br.com.vrosendo.events;

import java.util.Date;

public class SubscribeEvent {

	private final Long idUser;
	private final Long idPlan;
	private final String cardNumber;
	private final String cardDigit;
	private final Date eventDate;
	
	public SubscribeEvent(Long idUser, Long idPlan, String cardNumber, String cardDigit, Date eventDate) {
		this.idUser = idUser;
		this.idPlan = idPlan;
		this.cardNumber = cardNumber;
		this.cardDigit = cardDigit;
		this.eventDate = eventDate;
	}
	
	public Long getIdUser() {
		return idUser;
	}
	public Long getIdPlan() {
		return idPlan;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public String getCardDigit() {
		return cardDigit;
	}
	public Date getEventDate() {
		return eventDate;
	}
	
	
	
}
