package com.juls.rest.util;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.Good;
import com.juls.model.User;
import com.juls.persist.GoodDAOImpl;
import com.juls.persist.IDAO;
import com.juls.persist.UserDAOImpl;


@Controller
@RequestMapping(value = "/testdata/insert")
public class TestDataController {
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String insertUsers() {
		
		IDAO<User> userDAO = new UserDAOImpl();
		
		User user1 = new User("user1@gmail.com", "pass1");
		User user2 = new User("user2@gmail.com", "pass2");
		User user3 = new User("user3@gmail.com", "pass3");
		
		userDAO.insert(user1);
		userDAO.insert(user2);
		userDAO.insert(user3);
		
		
		return "<html><body><h3>Success insert users!</h3></body></html>";
	}
	
	@RequestMapping(value = "/goods", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String insertData() {
		
		IDAO<Good> goodDAO = new GoodDAOImpl();
		Good good1 = new Good("Good1", 99.99f, "This is a test description for the first good.",
				"Cereals", "200g", "Rice, wheat, sugar, crops");
		 
		Good good2 = new Good("Good2", 5.4f, "This is a test description for the second good.",
				"Salads", "100g", "cheese, cabbage leaves, beans, salad leaves, salt");
		
		Good good3 = new Good("Good3", 12.3f, "This is a test description fot the third good.",
				"Juice", "0.5 litres", "Fresh apple juice.");
		
		goodDAO.insert(good1);
		goodDAO.insert(good2);
		goodDAO.insert(good3);
		
		return "<html><body><h3>Success insert goods!</h3></body></html>";
	}
	
//	private void insertTestUsers() {
		// Users
		
		// Goods
		
		// UserDetails
//		IDAO<UserDetails> userDetailsDAO = new UserDetailsDAOImpl();
//		
//		UserDetails userDetails1 = new UserDetails("fName1", "lName1", "address1", "mobNum1");
//		UserDetails userDetails2 = new UserDetails("fName2", "lName2", "address2", "mobNum2");
//		UserDetails userDetails3 = new UserDetails("fName3", "lName3", "address3", "mobNum3");
//		
//		userDetailsDAO.insert(userDetails1);
//		userDetailsDAO.insert(userDetails2);
//		userDetailsDAO.insert(userDetails3);

//	}
	
}
