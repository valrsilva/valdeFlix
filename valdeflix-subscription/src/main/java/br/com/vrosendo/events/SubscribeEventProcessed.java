package br.com.vrosendo.events;

import java.util.Date;

public class SubscribeEventProcessed extends SubscribeEvent{

	public SubscribeEventProcessed(Long idUser, Long idPlan, String cardNumber, String cardDigit) {
		super(idUser, idPlan, cardNumber, cardDigit, new Date());
	}
	
}
