package com.juls.rest.controllers.login;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bionic.web.store.model.entity.User;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping(value= "/user", method = RequestMethod.GET, headers = "Accept=*/*",produces = "application/json")
	public @ResponseBody User userLogin() {
		User user = new User("Kirill", "Beregovoy", "mypass", 324324, "mymail@ff.ff");
		return user;
    }
}
