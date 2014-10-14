package com.juls.rest.controllers.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.User;
import com.juls.rest.services.OrderService;


@Controller
@RequestMapping("/order")
@Scope("request")
public class OrderController {
	
	@Autowired
	User currentUser;
	
//	@RequestMapping(value = "/cartId", method = RequestMethod.GET, produces = "application/json")
//	public @ResponseBody String getCartId() {
//		
//	}
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String сonfirm() {
		if(new OrderService().sendConfirmationEmail(currentUser))
			return "{\"message :\" Your order has been processed,"
					+ " please check your e-mail!}";
		else {
			return "{\"message:\" Something went wrong, please reload page "
					+ "and make sure for all fields is filled!}";
		}
	}
	
}
