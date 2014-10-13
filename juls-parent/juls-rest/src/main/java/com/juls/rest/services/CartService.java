package com.juls.rest.services;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.Cart;
import com.juls.model.CartGood;
import com.juls.model.Good;
import com.juls.model.Order;
import com.juls.model.User;
import com.juls.persist.CartDAOImpl;
import com.juls.persist.HibernateUtil;
import com.juls.persist.IDAO;
import com.juls.persist.OrderDAOImpl;


public class CartService {
	
	private static final int UNKNOWN_USER = 1;
	private static final int UNKNOWN_GOOD = 2;
	private static final int NEW_GOOD_WAS_ADDED = 3;
	private static final int GOOD_IS_IN_CART = 4;
	
	private static final String EMPTY_JSON = "[]";
	
	
	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	public int addToCart(User user, String goodId) {
		
		if(user == null || user.getId() == null)
			return UNKNOWN_USER;
		if(goodId == null)
			return UNKNOWN_GOOD;
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		user = (User)session.get(User.class, user.getId());
		Cart cart = getCartOrCreateIfNotExist(user, session);
		
		
		if(isGoodInCart(cart, goodId)) {
			tr.commit();
			return GOOD_IS_IN_CART;
		} else {
			Good good = (Good)session.get(Good.class, goodId);
			cart.addGood(good);
			session.saveOrUpdate(cart);
			session.update(user);
		}
		
		tr.commit();
		
		new CartStateService().calculateTotalPrice(user, cart.getId());
		
		return NEW_GOOD_WAS_ADDED;
	}

	private boolean isGoodInCart(Cart cart, String goodId) {	
		List<CartGood> cart2good = cart.getCartGoods();
		for (CartGood cartGood : cart2good) {
			if(cartGood.getGood().getId().equals(goodId))
				return true;
		}
		return false;
	}
	
	public Cart getCartOrCreateIfNotExist(User user, Session session) {
		User inTableUser = (User) session.get(User.class, user.getId()); 
		Cart cart = inTableUser.getUserCart();
		if(cart == null) {
			cart = new Cart(Cart.DEFAULT_CART_STATUS);
			user.setUserCart(cart);
			session.update(user);
		}
		return cart;
	}	
	
	public String getAllGoods(User user) {
		if(user == null || user.getId() == null)
			return EMPTY_JSON;
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		user = (User)session.get(User.class, user.getId());
		Cart cart = user.getUserCart();
		
		if(cart == null || cart.getCartGoods().size() == 0) {
			tr.commit();
			return EMPTY_JSON;
		}
		
		List<CartGood> cart2good = cart.getCartGoods();
		StringBuilder goodsJSON = new StringBuilder("{\"id\":\"" + cart.getId() + 
				"\",\"goods\":[");
		
		Good good = null;
		for (CartGood cartGood : cart2good) {
			if(good != null)
				goodsJSON.append(",");
			good = cartGood.getGood();
			goodsJSON.append("{\"name\":\"" + good.getName() + "\",");
			goodsJSON.append("\"id\":\"" + good.getId() + "\",");
			goodsJSON.append("\"amount\":" + cartGood.getGoodAmount() + ",");
			goodsJSON.append("\"price\":" + good.getPrice() + "}");
		}
		
		tr.commit();
		
		goodsJSON.append("]}");
		
		return goodsJSON.toString();
	}
	
	public boolean createOrder(User currentUser){
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		String id = currentUser.getId();
		
		try {
		
			Query query = session.createQuery(" from User user where id = '"+id+"' ");
		    User user = (User) query.list().get(0);    
		    Cart cart = user.getUserCart();
		    
		    Order order = new Order(user , cart);
		    		    
//		    if (!(new OrderDAOImpl().insert(order)))
//		    	new OrderDAOImpl().update(order);
		    
		    new OrderDAOImpl().insert(order);
		    
		    tr.commit();
			return true;
			
		} catch(Exception ex){			
			tr.rollback();
			return false;	
			
		}
	}
	
	/*
	 *  Method looks through all orders 
	 * and return true if some order already using current cart;
	 */
	
	public boolean isCartInOrders(Cart cart) {
		
		List <Order> ordersList = new OrderDAOImpl().getAll();
		System.out.println(ordersList.toString());
		Iterator<Order> itr = ordersList.listIterator();
		boolean flag = false;
		
			while(itr.hasNext()) {
				Order actualOrder = itr.next();
				if (actualOrder.getCurrentCart().getId().equals(cart.getId())){
					flag = true;
					}
			}
				return flag;
		}
		
}
	
