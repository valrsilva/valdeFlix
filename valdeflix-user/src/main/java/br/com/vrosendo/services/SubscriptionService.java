package br.com.vrosendo.services;

import br.com.vrosendo.events.SubscribeEvent;
import rx.Observer;
import rx.functions.Action1;

public interface SubscriptionService {

	void subscribe(long idUser, long idPlan, String cardNumber, String cardDigit);
	void subscribeToSubscribeEvents(Observer<SubscribeEvent> subscriber);
	void subscribeToSubscribeEvents(Action1<SubscribeEvent> onNext);
	
}
