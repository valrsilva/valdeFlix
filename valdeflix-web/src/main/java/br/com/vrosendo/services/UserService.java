package br.com.vrosendo.services;


import br.com.vrosendo.domain.User;
import br.com.vrosendo.dtos.UserDto;

public interface UserService {

	UserDto login(String username, String password);
	void create(User user);
	
}
