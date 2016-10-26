package br.com.vrosendo.services.impl;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.vrosendo.domain.User;
import br.com.vrosendo.events.UserEvent;
import br.com.vrosendo.events.UserEventCreate;
import br.com.vrosendo.repository.UserRepository;
import br.com.vrosendo.services.UserService;
import rx.Observer;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

@Service
public class UserServiceImpl implements UserService{
	
	private final PublishSubject<UserEvent> userEventSubject;
	
	@Autowired
	UserRepository userRepository;
	
	public UserServiceImpl(){	
		userEventSubject = PublishSubject.create();
	}
	
	@Autowired
    private DiscoveryClient discoveryClient;
	
	/*@Autowired
    private RestTemplate restTemplate;*/
	
	@Override
	public User login(String username, String password){
		
		/*String notificationsStatus = notificationsStatus();
		System.out.println(notificationsStatus.toString());
		
		 // use the "smart" Eureka-aware RestTemplate
        ResponseEntity<List<Object>> exchange =
                this.restTemplate.exchange(
                        "http://valdeflix-media/media?idUser=1",
                        org.springframework.http.HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Object>>() {},
                        (Object) "mstine");

        exchange.getBody().forEach(System.out::println);*/
        
		return userRepository.findOneByUsernameAndPassword(username, password);
		
	}
	
	@Override
	public boolean logout(Long id){
		
		return true;
		
	}
	
	@Override
	public void create(User user){
		
		System.out.println("CREATING USER.....");
		userRepository.save(user);
		UserEvent userEvent = new UserEventCreate(user.getUsername(), user.getEmail());
		userEventSubject.onNext(userEvent);
		
	}

	@Override
	public void subscribeToUserEvents(Observer<UserEvent> subscriber) {
		userEventSubject.subscribe(subscriber);
	}

	@Override
	public void subscribeToUserEvents(Action1<UserEvent> onNext) {
		userEventSubject.doOnNext(onNext).subscribe();
	}
	
	@HystrixCommand(fallbackMethod = "statusNotFound")
	public String notificationsStatus() {  
		
		discoveryClient.getInstances("photo-service").forEach((ServiceInstance s) -> {
            System.out.println(ToStringBuilder.reflectionToString(s));
        });
		
	    return discoveryClient.getInstances("valdeflix-media").get(0).getServiceId();
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

