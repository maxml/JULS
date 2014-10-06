package org.juls.rest;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.juls.model.Cart;
import com.juls.model.Good;
import com.juls.model.Order;
import com.juls.model.User;
import com.juls.persist.CartDAOImpl;
import com.juls.persist.GoodDAOImpl;
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
	public void insertTest() throws Exception {
		userDao = new UserDAOImpl();
		cartDao = new CartDAOImpl();
		orderDao = new OrderDAOImpl();
		user = userDao.getByEmail("user2@gmail.com"); 
		cart = cartDao.getAll().get(0);
		
		/*Create new Order */
		expectedOrder = new Order(user, cart);
		id = expectedOrder.getId();
		
		assertTrue(orderDao.insert(expectedOrder));
	}

	@Test
	public void getByIdTest() throws Exception{
		
		actualOrder = new Order();
		actualOrder = orderDao.getById(id);
		
		assertEquals(expectedOrder.getId(), actualOrder.getId());
	}
	
	@After 
	public void deleteTest() throws Exception{
		
		assertTrue(orderDao.delete(expectedOrder));
		assertTrue(orderDao.delete(actualOrder));
		assertTrue(orderDao.getAll().size() == 0);
	}
}