package com.juls.rest.services;

import org.springframework.stereotype.Service;

import com.juls.model.User;
import com.juls.persist.UserDAOImpl;

@Service
public class UserService {

	public boolean update(String email, String oldPassword,
			String newPassword1, String newPassword2, String fName, String lName,
			String address, String phone){
		
		User currUser = new UserDAOImpl().getByEmail(email);
		
		if (null != currUser){
			
			if (!oldPassword.isEmpty() && !newPassword1.isEmpty() && !newPassword2.isEmpty() 
					&& newPassword1.equals(newPassword2) && currUser.getPassword().equals(oldPassword)){
				currUser.setPassword(newPassword1);
			}
			currUser.getAdditionalInfo().setFirstName(fName);
			currUser.getAdditionalInfo().setLastName(lName);
			currUser.getAdditionalInfo().setAddress(address);
			currUser.getAdditionalInfo().setMobilePhoneNumber(phone);
			return new UserDAOImpl().update(currUser);
		}
		return false;
		
	}
}