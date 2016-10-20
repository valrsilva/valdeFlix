package br.com.vrosendo.services;

import br.com.vrosendo.domain.User;
import br.com.vrosendo.events.UserEvent;
import rx.Observer;
import rx.functions.Action1;

public interface UserService {

	User login(String username, String password);
	boolean logout(Long id);
	void create(User user);
	void subscribeToUserEvents(Observer<UserEvent> subscriber);
	void subscribeToUserEvents(Action1<UserEvent> onNext);
	
}
