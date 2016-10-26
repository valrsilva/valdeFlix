package br.com.vrosendo.services.impl;

import org.springframework.stereotype.Service;

import br.com.vrosendo.services.BillingService;

@Service
public class BillingServiceImpl implements BillingService {

	@Override
	public void processPayment() {
		
		System.out.println("PROCESS BILLING...");
		
	}
	
}
