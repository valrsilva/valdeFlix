package br.com.vrosendo.dtos;

import br.com.vrosendo.domain.Plan;
import br.com.vrosendo.domain.User;

public class SubscribeDto {

	private User user;
	private Plan plan;
	private String cardNumber;
	private String cardDigit;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardDigit() {
		return cardDigit;
	}
	public void setCardDigit(String cardDigit) {
		this.cardDigit = cardDigit;
	}
	
	
	
}
