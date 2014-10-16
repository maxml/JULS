package com.juls.rest.controllers.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.User;
import com.juls.model.UserDetails;
import com.juls.rest.services.OrderService;


@Controller
@RequestMapping("/order")
@Scope("request")
public class OrderController {
	
	@Autowired
	User currentUser;
	
	@RequestMapping(value = "/confirm", method = RequestMethod.GET, produces = "text/html")
	public @ResponseBody String showAllItems(@RequestParam("firstname") String firstName,
											 @RequestParam("lastname") String lastName,
											 @RequestParam("address") String address,
											 @RequestParam("phonenumber") String phone) {
		OrderService service = new OrderService();
		service.setAdditionalInfo(currentUser, firstName, lastName, address, phone);
		if(service.sendConfirmationEmail(currentUser))
			return generateHtmlWithMessage("Your order has been processed, please check your e-mail!");

		else {
			return generateHtmlWithMessage("Something went wrong, please reload page and make sure for all fields is filled!");
		}
	}
	
	@RequestMapping(value="/details", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody UserDetails getUser(){
		OrderService service = new OrderService();
		return service.getOrderDetails(currentUser);
	}
	
	private String generateHtmlWithMessage(String message) {
		StringBuilder builder = new StringBuilder("<html><body><h3>");
		builder.append(message);
		builder.append("</h3><form action='/main.html' method='get' > "
				+ "<input type='submit' id='backtoMain' value='Back to Main'> </input>"
				+ " </form></body></html>");
		builder.append("");
		return builder.toString();
	}
	
}
