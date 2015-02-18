package com.juls.rest.controllers.sign;

import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/signout", produces = "application/json")
public class SignOutController {
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String signOut(HttpSession session) {
		session.invalidate();
		
		return "{\"link\":\"http://localhost:8080/index.html\"}";
	}
}
