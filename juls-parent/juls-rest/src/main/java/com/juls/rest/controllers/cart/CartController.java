package com.juls.rest.controllers.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.User;
import com.juls.persist.UserDAOImpl;

@Controller
@RequestMapping(value = "/cart")
@Scope("request")
public class CartController {
	
	@Autowired
	User currentUser;
	
	@RequestMapping(value = "/put/{itemUUID}", method = RequestMethod.PUT, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String addToCart(@PathVariable("itemUUID") String itemUUID) {
		
		StringBuilder sb = new StringBuilder();
		
//		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//		HttpSession session = attributes.getRequest().getSession();
//		User user = (User)session.getAttribute("currentUser");
//		User currntUser = new UserDAOImpl().getByEmail(user.getEmail());
		
//		sb.append("user.getEmail() = " + user.getEmail());
//		sb.append("user.getPassword()" + user.getPassword());
		sb.append("currentUser.getEmail() = " + currentUser.getEmail());
		sb.append("currentUser.getPassword()" + currentUser.getPassword());
		sb.append("currentUser.getId() = " + currentUser.getId());
		
//		if (currntUser == null) 
//			sb.append("Server error. currntUser == null");
//		else if(currntUser.getId() == null)
//			sb.append("Server error. currentUser.getId() == null");
//		else {
//			
//			// adding item..
//			
//			sb.append("item.id=" + itemUUID + " added to cart");
//		}
		
		return sb.toString();
	}
}
