package com.juls.rest.controllers.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.juls.model.User;
import com.juls.persist.UserDAOImpl;
import com.juls.rest.entityContainers.UserEntityContainer;

@Controller
@RequestMapping(value="/new")
@Scope("request")
public class RegistrationController {
	
	@Autowired
	User currentUser;
	
	@RequestMapping(value="/user", method=RequestMethod.POST, headers = "Accept=*/*", produces="application/json")
	public String register(@ModelAttribute("userPrototype") UserEntityContainer userPrototype){
		User currntUser = new User(userPrototype.getEmail(), userPrototype.getPassword());
		if(new UserDAOImpl().insert(currntUser)){
			/*ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
			HttpSession session = attributes.getRequest().getSession();*/
			currntUser.setId(currntUser.getId());
			currntUser.setEmail(currntUser.getEmail());
			currntUser.setPassword(currntUser.getPassword());
			currntUser.setAdditionalInfo(currntUser.getAdditionalInfo());
			//session.setAttribute("currentUser", currentUser);
			return "redirect:" + "../static/html/main.html";
		}
		else{
			return "";
		}
	}
}