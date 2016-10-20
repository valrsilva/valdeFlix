package br.com.vrosendo.monitors;

import br.com.vrosendo.events.SubscribeEvent;
import br.com.vrosendo.services.BillingService;
import br.com.vrosendo.services.SubscriptionService;

public class UserBillingMonitor {

	public final BillingService billingService;
	
	public UserBillingMonitor(BillingService billingService, SubscriptionService subscriptionService){
		this.billingService = billingService;
		subscriptionService.subscribeToSubscribeEvents(this::handleSubscribeEvents);
	}
	
	private void handleSubscribeEvents(SubscribeEvent event){
		System.out.println("HANDLING BILLING EVENT...");
		billingService.processPayment();
	}
}
