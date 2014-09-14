package com.juls.rest.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.Cart;
import com.juls.model.CartGood;
import com.juls.model.Good;
import com.juls.model.User;
import com.juls.persist.CartDAOImpl;
import com.juls.persist.GoodDAOImpl;
import com.juls.persist.HibernateUtil;
import com.juls.persist.IDAO;
import com.juls.persist.UserDAOImpl;

public class CartService {
	
	private static final int UNKNOWN_USER = 1;
	private static final int UNKNOWN_GOOD = 2;
	private static final int NEW_GOOD_WAS_ADDED = 3;
	private static final int GOOD_IS_IN_CART = 4;
	
	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	public int addToCart(User user, String goodId) {
		
		if(user == null || user.getId() == null)
			return UNKNOWN_USER;
		if(goodId == null)
			return UNKNOWN_GOOD;
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		user = (User)session.get(User.class, user.getId());
//		System.out.println("user.cart.id=" + user.getUserCart().getId());
		Cart cart = getCartOrCreateIfNotExist(user);
		
		if(isGoodInCart(cart, goodId)) {
//			System.out.println("Good in cart!");
			tr.commit();
			return GOOD_IS_IN_CART;
		} else {
//			System.out.println("Good not in cart!");
			Good good = (Good)session.get(Good.class, goodId);
			cart.addGood(good);
			session.saveOrUpdate(cart);
			session.update(user);
		}
		
//		cartGood.setGoodAmount(1);
//		cartGood.setGood();
//		cartGood.setCart(cart);
//		
//		cart.getCartGoods().add(cartGood1);
//		
//		session.save(cart);
		tr.commit();
		
		return NEW_GOOD_WAS_ADDED;
	}

	private boolean isGoodInCart(Cart cart, String goodId) {	
		List<CartGood> cart2good = cart.getCartGoods();
		System.out.println("cart2good.amount=" + cart2good.size());
		for (CartGood cartGood : cart2good) {
			if(cartGood.getGood().getId().equals(goodId))
				return true;
		}
		return false;
	}
	
	private Cart getCartOrCreateIfNotExist(User user) {
		Cart cart = user.getUserCart();
		if(cart == null) {
			cart = new Cart(Cart.DEFAULT_CART_STATUS);
			user.setUserCart(cart);
		}
		return cart;
	}	
}
