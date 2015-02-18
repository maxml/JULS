
package com.juls.rest.controllers.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.User;
import com.juls.model.UserDetails;
import com.juls.persist.UserDAOImpl;
import com.juls.persist.UserDetailsDAOImpl;
import com.juls.rest.services.Redirector;
import com.juls.rest.services.UserService;

@Controller
@RequestMapping(value="/user")
@Scope("request")
public class UserController {

	@Autowired
	private User currentUser;
	
	@RequestMapping(value="/curr", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody User getUser(){
		return currentUser;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST, headers="Accept=*/*", produces="application/json")
	public String updateUserInfo(@RequestParam("email") String email, @RequestParam("oldpassword")String oldPassword, 
			@RequestParam("newpassword1")String newPassword1, @RequestParam("newpassword2")String newPassword2, 
			@RequestParam("firstname")String firstName, @RequestParam("lastname")String lastName, 
			@RequestParam("address")String address, @RequestParam("phonenumber")String phone){
		
		if (new UserService().update(email, oldPassword, newPassword1, newPassword2, 
				firstName, lastName, address, phone)){
			
			User currntUser = new UserDAOImpl().getByEmail(email);
			currentUser.setPassword(currntUser.getPassword());
			currentUser.setAdditionalInfo(currntUser.getAdditionalInfo());					
			return Redirector.redirectToIndex();
		
		}			
		return "Wrong data";
	}
}
