package br.com.vrosendo.events;

import java.util.Date;

public class SubscribeEventCreate extends SubscribeEvent{

	public SubscribeEventCreate(Long idUser, Long idPlan, String cardNumber, String cardDigit) {
		super(idUser, idPlan, cardNumber, cardDigit, new Date());
	}
	
}
