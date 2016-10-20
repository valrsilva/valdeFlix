package br.com.vrosendo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.vrosendo.domain.User;
import br.com.vrosendo.monitors.EmailMonitor;
import br.com.vrosendo.services.EmailService;
import br.com.vrosendo.services.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	static EmailMonitor emailMonitor = null;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public void register(@RequestBody User user){	
		
		if(emailMonitor == null){
			emailMonitor = new EmailMonitor(emailService, userService);
		}
		
		userService.create(user);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
