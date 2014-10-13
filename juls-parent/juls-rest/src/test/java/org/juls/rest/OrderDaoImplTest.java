package org.juls.rest;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.juls.model.Cart;
import com.juls.model.Order;
import com.juls.model.User;
import com.juls.persist.CartDAOImpl;
import com.juls.persist.OrderDAOImpl;
import com.juls.persist.UserDAOImpl;

import static org.junit.Assert.*;

/**
 * 
 * Test for OrderDAOimpl
 * @author Matvey Mitnitskyi
 * 
 */

public class OrderDaoImplTest {
	private Order expectedOrder, actualOrder;
	private OrderDAOImpl orderDao;
	private UserDAOImpl userDao;
	private CartDAOImpl cartDao;
	private Cart cart;
	private User user;
	private String id;
	
	@Before
	@Ignore
	public void insertTest() throws Exception {
		userDao = new UserDAOImpl();
		cartDao = new CartDAOImpl();
		orderDao = new OrderDAOImpl();
		
		/*Inserting test data*/
		userDao.insert(new User("testuser@mail.com", "testpass"));		
		user = userDao.getByEmail("testuser@mail.com");
		
		/*
		 * Creating cart with TEST_CART_STATUS (111)
		 * prevents deleting wrong data while test
		 * executing.
		 */
		cartDao.insert(new Cart(111));		
		List <Cart> cartList = cartDao.getAll();
		for(Cart cartIterator : cartList) {
			if (cartIterator.getStatus() == 111) 
				cart = cartIterator;
		}
	
		expectedOrder = new Order(user, cart);
		id = expectedOrder.getId();
			assertTrue(orderDao.insert(expectedOrder));
	}

	@Test
	@Ignore
	public void getByIdTest() throws Exception{
		actualOrder = new Order();
		actualOrder = orderDao.getById(id);
		assertEquals(expectedOrder.getId(), actualOrder.getId());
	}
	
	@After 
	@Ignore
	public void deleteTest() throws Exception{
		
		
		assertTrue(orderDao.delete(expectedOrder));
		assertTrue(orderDao.getAll().size() == 0);
		assertTrue(userDao.delete(user));
		assertTrue(cartDao.delete(cart));
	}
}