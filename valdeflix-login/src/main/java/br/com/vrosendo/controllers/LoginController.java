package br.com.vrosendo.controllers;

import java.util.HashMap;

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
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UserService userService;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public HashMap<String, String> login(@RequestBody User user){	
		
		HashMap<String, String> objRet = new HashMap<>();
		
		User loggedUser = userService.login(user.getUsername(), user.getPassword());
		
		if(loggedUser == null){
			objRet.put("msg", "notFound");
		}else if(loggedUser.getPlan() == null){
			objRet.put("msg", "emptyPlan");
		}
		
		return objRet;
		
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public boolean logout(@RequestBody User user){
		return userService.logout(user.getId());
	}
	
}
