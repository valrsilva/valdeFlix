package br.com.vrosendo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	@Override
	public User login(String username, String password){
		
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
	
}
