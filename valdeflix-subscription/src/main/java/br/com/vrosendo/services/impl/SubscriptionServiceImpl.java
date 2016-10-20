package br.com.vrosendo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vrosendo.domain.Plan;
import br.com.vrosendo.domain.User;
import br.com.vrosendo.events.SubscribeEvent;
import br.com.vrosendo.events.SubscribeEventCreate;
import br.com.vrosendo.repository.PlanRepository;
import br.com.vrosendo.repository.UserRepository;
import br.com.vrosendo.services.SubscriptionService;
import rx.Observer;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	private final PublishSubject<SubscribeEvent> subscriptionEventSubject;
	
	public SubscriptionServiceImpl(){
		subscriptionEventSubject = PublishSubject.create();
	}
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PlanRepository planRepository;
	
	@Override
	public void subscribe(long idUser, long idPlan, String cardNumber, String cardDigit) {
		
		User user = userRepository.findOne(idUser);
		Plan plan = planRepository.findOne(idPlan);
		user.setPlan(plan);
		userRepository.save(user);
		
		System.out.println("PROCESSING SUBSCRIPTION...");
		System.out.println("USER: " + idUser);
		System.out.println("PLAN: " + idPlan);
		System.out.println("cardnumber: " + cardNumber);
		System.out.println("carddigit: " + cardDigit);
		
		SubscribeEventCreate subscribeEvent = new SubscribeEventCreate(idUser, idPlan, cardNumber, cardDigit);
		subscriptionEventSubject.onNext(subscribeEvent);
		
	}

	@Override
	public void subscribeToSubscribeEvents(Observer<SubscribeEvent> subscriber) {
		subscriptionEventSubject.subscribe(subscriber);
	}

	@Override
	public void subscribeToSubscribeEvents(Action1<SubscribeEvent> onNext) {
		subscriptionEventSubject.doOnNext(onNext).subscribe();
	}
	
}
