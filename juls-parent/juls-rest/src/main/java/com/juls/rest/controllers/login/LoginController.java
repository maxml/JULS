package com.juls.rest.controllers.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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

	private static final int THIRTY_DAYS_IN_SECONDS = 2592000;
	private static final int FIVE_MINUTES_IN_SECONDS = 300;
	
	@Autowired
	User currentUser;
	
	@RequestMapping(value= "/user", method = RequestMethod.POST, produces = "application/json")
	public String userLogin(HttpSession session,
							@RequestParam(value = "email") String requestEmail,
							@RequestParam(value = "password") String requestPassword,
							@RequestParam(required = false, value = "remember") boolean requestRemember) {

		if(requestRemember)
			session.setMaxInactiveInterval(THIRTY_DAYS_IN_SECONDS);
		else
			session.setMaxInactiveInterval(FIVE_MINUTES_IN_SECONDS);
		
		User currntUser = new UserDAOImpl().getByEmail(requestEmail);	
		if (currntUser != null){
			if (currntUser.getPassword().equals(requestPassword)){
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
