
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
		currntUser.getAdditionalInfo().setFirstName(firstName);
		currntUser.getAdditionalInfo().setLastName(lastName);
		currntUser.getAdditionalInfo().setAddress(address);
		currntUser.getAdditionalInfo().setMobilePhoneNumber(phone);
		if (null != currntUser){
			if (currntUser.getPassword().equals(oldPassword) && newPassword1.equals(newPassword2) 
					&& !newPassword1.isEmpty() && !newPassword2.isEmpty()){
				
				if(isValid(firstName, lastName, address, phone)){
					currentUser.setEmail(newEmail);
					currentUser.setPassword(newPassword1);
					currentUser.setAdditionalInfo(currntUser.getAdditionalInfo());
					new UserDAOImpl().update(currntUser);					
					return "redirect:" +  "../static/html/main.html";
				}
			}
		}
		return "Wrong data";
	}
	
	private boolean isValid(String fName, String lName, String address, String pNum){
		return ((!fName.isEmpty()) && (!lName.isEmpty()) && (!address.isEmpty()) && (!pNum.isEmpty()));
	}
}
