package br.com.vrosendo.services.impl;

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

import br.com.vrosendo.domain.User;
import br.com.vrosendo.dtos.UserDto;
import br.com.vrosendo.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    private DiscoveryClient discoveryClient;
	
	@Autowired
	@LoadBalanced
    private RestTemplate restTemplate;
	
	@Override
	public UserDto login(String username, String password){
		
		String notificationsStatus = notificationsStatus();
		System.out.println(notificationsStatus.toString());
		
		HttpEntity<User> request = new HttpEntity<>(new User(username,password));
		UserDto loggedUser = restTemplate.postForObject("http://valdeflix-user/login", request, UserDto.class);
		
		return loggedUser;
		
	}
	
	@Override
	public void create(User user){
		
		HttpEntity<User> request = new HttpEntity<>(user);
		restTemplate.postForObject("http://valdeflix-user/register", request, Object.class);
		
	}
	
	@HystrixCommand(fallbackMethod = "statusNotFound")
	public String notificationsStatus() {  
		
		discoveryClient.getInstances("valdeflix-user").forEach((ServiceInstance s) -> {
            System.out.println(ToStringBuilder.reflectionToString(s));
        });
		
	    return discoveryClient.getInstances("valdeflix-user").get(0).getServiceId();
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

