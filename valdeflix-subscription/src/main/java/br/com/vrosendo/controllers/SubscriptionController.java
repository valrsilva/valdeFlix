package br.com.vrosendo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vrosendo.dtos.SubscribeDto;
import br.com.vrosendo.monitors.UserBillingMonitor;
import br.com.vrosendo.services.BillingService;
import br.com.vrosendo.services.SubscriptionService;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

	static UserBillingMonitor userBillingMonitor = null;
	
	@Autowired
	SubscriptionService subscriptionService;
	
	@Autowired
	BillingService billingService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public void subscribe(@RequestBody SubscribeDto subscribeDto){	
		
		if(userBillingMonitor == null){
			userBillingMonitor = new UserBillingMonitor(billingService, subscriptionService);
		}
		
		subscriptionService.subscribe(subscribeDto.getUser().getId(), subscribeDto.getPlan().getId(), 
				subscribeDto.getCardNumber(), subscribeDto.getCardDigit());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
