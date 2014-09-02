package com.juls.rest.controllers.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.juls.model.User;
import com.juls.persist.UserDAOImpl;
import com.juls.rest.services.Redirector;

@Controller
@RequestMapping(value = "/login")
@Scope("request")
public class LoginController {

	@Autowired
	User currentUser;
	
	@RequestMapping(value= "/user", method = RequestMethod.POST, headers = "Accept=*/*",produces = "application/json")
	public String userLogin(@RequestParam("email") String email, @RequestParam("password") String password) {
		User currntUser = new UserDAOImpl().getByEmail(email);
		if (currntUser != null){
			if (currntUser.getPassword().equals(password)){
				currentUser.setId(currntUser.getId());
				currentUser.setEmail(currntUser.getEmail());
				currentUser.setPassword(currntUser.getPassword());
				currentUser.setRegStatus(currntUser.getRegStatus());
				currentUser.setToken(currntUser.getToken());
				currentUser.setAdditionalInfo(currntUser.getAdditionalInfo());
	
				return Redirector.redirectToMain(); 
			}
			else{
				return "<html><body><h3>Wrong username or password</h3></body></html>";
			}
		}
		return "";
    }
}
