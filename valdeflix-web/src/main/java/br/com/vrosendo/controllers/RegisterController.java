package br.com.vrosendo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vrosendo.domain.User;
import br.com.vrosendo.services.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	UserService userService;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public void register(@RequestBody User user){	
		
		userService.create(user);
				
	}
	
}
