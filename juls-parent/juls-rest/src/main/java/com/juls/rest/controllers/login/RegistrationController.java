package com.juls.rest.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.juls.model.User;
import com.juls.persist.UserDAOImpl;
import com.juls.rest.services.Redirector;
import com.juls.rest.services.RegistrationService;

@Controller
@RequestMapping(value="/new")
@Scope("request")
public class RegistrationController {
	
	@Autowired
	User currentUser;
	
	@RequestMapping(value="/user", method=RequestMethod.POST, headers = "Accept=*/*", produces="application/json")
	public String register(@RequestParam("email") String email, @RequestParam("password1") String password1, 
			@RequestParam("password2") String password2){
		RegistrationService regsvc = new RegistrationService();
		User currntUser = regsvc.register(email, password1, password2);
		if(regsvc.save(currntUser)){
			currentUser.setId(currntUser.getId());
			currentUser.setEmail(currntUser.getEmail());
			currentUser.setPassword(currntUser.getPassword());
			currentUser.setRegStatus(currntUser.getRegStatus());
			currentUser.setToken(currntUser.getToken());
			currentUser.setAdditionalInfo(currntUser.getAdditionalInfo());
			regsvc.sendConfirmationEmail(currntUser);
			return Redirector.redirectToIndex();
		}
		else{
			return "Error";
		}
	}
	
	@RequestMapping(value="/validate/{userToken}", method=RequestMethod.GET, produces="application/json")
	public String validate(@PathVariable("userToken") String userToken){
		RegistrationService regsrvc = new RegistrationService();
		if (regsrvc.validate(userToken)){
			if (currentUser.getId().equals(new UserDAOImpl().getByToken(userToken).getId()))
				currentUser.setRegStatus(User.REGISTERED);
			return Redirector.redirectToProfile();
		}	
		else
			return "Error";
	}
}