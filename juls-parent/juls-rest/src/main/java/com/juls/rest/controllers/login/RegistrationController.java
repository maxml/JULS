package com.juls.rest.controllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.User;
import com.juls.persist.UserDAOImpl;
import com.juls.rest.entityContainers.UserEntityContainer;

@Controller
@RequestMapping(value="/new")
public class RegistrationController {
	
	@RequestMapping(value="/user", method=RequestMethod.POST, headers = "Accept=*/*", produces="application/json")
	public String register(@ModelAttribute("userPrototype") UserEntityContainer userPrototype){
		User userToRegister = new User(userPrototype.getEmail(), userPrototype.getPassword());
		return new UserDAOImpl().insert(userToRegister) + "";
	}
}