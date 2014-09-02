package com.juls.rest.util;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.juls.model.Good;
import com.juls.model.Order;
import com.juls.model.Order2;
import com.juls.model.OrderGood;
import com.juls.model.User;
import com.juls.model.UserDetails;
import com.juls.persist.GoodDAOImpl;
import com.juls.persist.HibernateUtil;
import com.juls.persist.IDAO;
import com.juls.persist.OrderDAOImpl;
import com.juls.persist.UserDAOImpl;
import com.juls.persist.UserDetailsDAOImpl;


@Controller
@RequestMapping(value = "/testdata")
public class TestDataController {
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String insertUsers() {

		// UserDetail-s
		IDAO<UserDetails> userDetailsDAO = new UserDetailsDAOImpl();
		
		UserDetails userDetails1 = new UserDetails("fName1", "lName1", "address1", "mobNum1");
		UserDetails userDetails2 = new UserDetails("fName2", "lName2", "address2", "mobNum2");
		UserDetails userDetails3 = new UserDetails("fName3", "lName3", "address3", "mobNum3");
		
		userDetailsDAO.insert(userDetails1);
		userDetailsDAO.insert(userDetails2);
		userDetailsDAO.insert(userDetails3);

		// User-s
		IDAO<User> userDAO = new UserDAOImpl();
		
		User user1 = new User("user1@gmail.com", "pass1");
		User user2 = new User("user2@gmail.com", "pass2");
		User user3 = new User("user3@gmail.com", "pass3");
		
		user1.setAdditionalInfo(userDetails1);
		user2.setAdditionalInfo(userDetails2);
		user3.setAdditionalInfo(userDetails3);
		
		userDAO.insert(user1);
		userDAO.insert(user2);
		userDAO.insert(user3);
		
		// Good-s
		//IDAO<Good> goodDAO = new GoodDAOImpl();
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session  session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

		
		Good good1 = new Good("Good1", 99.99f);
		Good good2 = new Good("Good2", 5.4f);
		Good good3 = new Good("Good3", 12.3f);
		
		session.save(good1);
		session.save(good2);
		session.save(good3);
		
//		goodDAO.insert(good1);
//		goodDAO.insert(good2);
//		goodDAO.insert(good3);
//		
		// Order-s
		//IDAO<Order2> orderDAO = new OrderDAOImpl();

		Order2 order2 = new Order2(2);
		
		// OrderGood-s
		OrderGood orderGood1 = new OrderGood();
		orderGood1.setGoodAmount(3);
		orderGood1.setGood(good1);
		orderGood1.setOrder(order2);
		
		order2.getOrderGoods().add(orderGood1);
		
		session.save(order2);
        transaction.commit();
		

		return "<html><body><h3>Success insert!</h3></body></html>";
	}
//	
//	@RequestMapping(value = "/goods", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
//	public @ResponseBody String insertData() {
//		
//		IDAO<Good> goodDAO = new GoodDAOImpl();
//		
//		Good good1 = new Good("Good1", 99.99f);
//		Good good2 = new Good("Good2", 5.4f);
//		Good good3 = new Good("Good3", 12.3f);
//		
//		goodDAO.insert(good1);
//		goodDAO.insert(good2);
//		goodDAO.insert(good3);
//		
//		return "<html><body><h3>Success insert goods!</h3></body></html>";
//	}
//	
//	@RequestMapping(value = "/userdetails", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
//	public @ResponseBody String insertTestUsers() {
//
//		IDAO<UserDetails> userDetailsDAO = new UserDetailsDAOImpl();
//		
//		UserDetails userDetails1 = new UserDetails("fName1", "lName1", "address1", "mobNum1");
//		UserDetails userDetails2 = new UserDetails("fName2", "lName2", "address2", "mobNum2");
//		UserDetails userDetails3 = new UserDetails("fName3", "lName3", "address3", "mobNum3");
//		
//		userDetailsDAO.insert(userDetails1);
//		userDetailsDAO.insert(userDetails2);
//		userDetailsDAO.insert(userDetails3);
//
//		return "<html><body><h3>Success insert UserDetails!</h3></body></html>";
//	}
	
}
