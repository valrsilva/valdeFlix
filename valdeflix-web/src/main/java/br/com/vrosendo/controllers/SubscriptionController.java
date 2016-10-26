package br.com.vrosendo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vrosendo.dtos.SubscribeDto;
import br.com.vrosendo.services.SubscriptionService;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public void subscribe(@RequestBody SubscribeDto subscribeDto){	
		
		subscriptionService.subscribe(subscribeDto);
		
	}
	
}
