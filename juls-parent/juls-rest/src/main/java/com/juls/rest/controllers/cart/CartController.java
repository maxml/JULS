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
import com.juls.rest.services.CartService;

@Controller
@RequestMapping(value = "/cart")
@Scope("request")
public class CartController {
	
	@Autowired
	User currentUser;
	
	@RequestMapping(value = "/put/{itemUUID}", method = RequestMethod.PUT, produces = "application/json")
	public @ResponseBody String addToCart(@PathVariable("itemUUID") String itemUUID) {

		int answerCode = new CartService().addToCart(currentUser, itemUUID);
		
		return "{\"code\":" + answerCode + "}";
	}
}
