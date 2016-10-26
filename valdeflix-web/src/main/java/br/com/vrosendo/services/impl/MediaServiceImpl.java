package br.com.vrosendo.services.impl;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.vrosendo.domain.Group;
import br.com.vrosendo.services.MediaService;

@Service
public class MediaServiceImpl implements MediaService{

	@Autowired
	@LoadBalanced
    private RestTemplate restTemplate;
	
	@Autowired
    private DiscoveryClient discoveryClient;
	
	String returnStr;
	
	@Override
	public List<Group> listMediaByUser(Long id) {
		
		String notificationsStatus = notificationsStatus();
		System.out.println("status=" + notificationsStatus);
		
		HttpEntity<Long> request = new HttpEntity<>(id);
		List<Group> loggedUser = restTemplate.getForObject("http://valdeflix-media/media?idUser=1", List.class);
		
		return loggedUser;
		
	}
	
	@HystrixCommand(fallbackMethod = "statusNotFound")
	public String notificationsStatus() {  
		
		discoveryClient.getInstances("valdeflix-media").forEach((ServiceInstance s) -> {
			returnStr = s.getServiceId();
        });
	    return returnStr;
	    
	}

	public String statusNotFound() {  
	    return InstanceStatus.DOWN.toString();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
}
