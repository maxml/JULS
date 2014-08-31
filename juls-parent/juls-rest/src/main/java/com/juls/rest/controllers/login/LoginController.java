package com.juls.rest.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.juls.model.User;
import com.juls.persist.UserDAOImpl;
import com.juls.rest.entityContainers.UserEntityContainer;
import com.juls.rest.services.Redirector;

@Controller
@RequestMapping(value = "/login")
@Scope("request")
public class LoginController {

	@Autowired
	User currentUser;
	
	@RequestMapping(value= "/user", method = RequestMethod.POST, headers = "Accept=*/*",produces = "application/json")
	public String userLogin(@ModelAttribute("user") UserEntityContainer user) {
		User currntUser = new UserDAOImpl().getByEmail(user.getEmail());
		if (currntUser != null){
			currentUser.setId(currntUser.getId());
			currentUser.setEmail(currntUser.getEmail());
			currentUser.setPassword(currntUser.getPassword());
			currentUser.setRegStatus(currntUser.getRegStatus());
			currentUser.setToken(currntUser.getToken());
			currentUser.setAdditionalInfo(currntUser.getAdditionalInfo());

			return Redirector.redirectToMain(); 
		}
		return "";
    }
}
