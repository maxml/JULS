package com.juls.rest.controllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.juls.model.User;
import com.juls.persist.UserDAOImpl;
import com.juls.rest.entityContainers.UserEntityContainer;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(value= "/user", method = RequestMethod.POST, headers = "Accept=*/*",produces = "application/json")
	public String userLogin(@ModelAttribute("user") UserEntityContainer user) {
		User resultUser = new UserDAOImpl().getByEmail(user.getEmail());
		if (resultUser != null)
			return "redirect:" + "../static/html/main.html";
		else
			return "";
    }
}
