
package com.juls.rest.controllers.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.juls.model.User;
import com.juls.model.UserDetails;
import com.juls.persist.UserDAOImpl;
import com.juls.persist.UserDetailsDAOImpl;

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
	public String updateUserInfo(@RequestParam("email") String email, @RequestParam("newemail")String newEmail,
				@RequestParam("oldpassword")String oldPassword, @RequestParam("newpassword1")String newPassword1, 
				@RequestParam("newpassword2")String newPassword2, @RequestParam("firstname")String firstName, 
				@RequestParam("lastname")String lastName, @RequestParam("address")String address, 
				@RequestParam("phonenumber")String phone){
		
		/*ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpSession session = attributes.getRequest().getSession();*/
		User currntUser = new UserDAOImpl().getByEmail(email);//(User)session.getAttribute("currentUser");
				
		if (null != currntUser){
			if (currntUser.getPassword() == oldPassword && newPassword1 == newPassword2 
					&& newPassword1 != "" && newPassword2 != ""){
				
				currentUser.setEmail(newEmail);
				currentUser.setPassword(newPassword1);
				if(isValid(firstName, lastName, address, phone)){
					UserDetails userDetails = new UserDetails(firstName, lastName, address, phone);
					currentUser.setAdditionalInfo(userDetails);
					new UserDetailsDAOImpl().insert(userDetails);
					new UserDAOImpl().update(currentUser);					
					return "redirect:" +  "../static/html/main.html";
				}
			}
		}
		return "Wrong data";
	}
	
	private boolean isValid(String fName, String lName, String address, String pNum){
		return ((fName != null) && (lName != null) && (address != null) && (pNum != null));
	}
}
