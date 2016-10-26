package br.com.vrosendo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.vrosendo.dtos.SubscribeDto;
import br.com.vrosendo.services.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	@Autowired
	@LoadBalanced
    private RestTemplate restTemplate;
	
	@Override
	public void subscribe(SubscribeDto subscribeDto) {
		
		HttpEntity<SubscribeDto> request = new HttpEntity<>(subscribeDto);
		restTemplate.postForObject("http://valdeflix-user/subscription", request, Object.class);
		
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
