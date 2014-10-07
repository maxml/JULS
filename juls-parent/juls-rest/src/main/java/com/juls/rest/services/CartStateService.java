package com.juls.rest.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.juls.model.Cart;
import com.juls.model.CartGood;
import com.juls.model.CartGoodId;
import com.juls.model.Good;
import com.juls.model.User;
import com.juls.persist.CartDAOImpl;
import com.juls.persist.CartGoodDAOImpl;
import com.juls.persist.GoodDAOImpl;
import com.juls.persist.HibernateUtil;
import com.juls.persist.IDAO;
import com.juls.rest.dto.CartItemDTO;
import com.juls.rest.dto.CartStateDeltaDTO;
import com.juls.rest.dto.GoodDeltaDTO;

public class CartStateService {
	
	private static final int SUCCESS = 0;
	private static final int INVALID_ARGUMENT = -1;
	
	public int changeGoodsAmount(CartStateDeltaDTO cartStateDelta) {
		if(cartStateDelta == null)
			return INVALID_ARGUMENT;
		
		Cart cart = new CartDAOImpl().getById(cartStateDelta.getCartId());
		IDAO<Good> goodDAO = new GoodDAOImpl();
		CartGoodDAOImpl cartGoodDAO = new CartGoodDAOImpl();
		
		for (GoodDeltaDTO goodDTO : cartStateDelta.getGoodDelta()) {
			Good good = goodDAO.getById(goodDTO.getGoodId());
			CartGoodId cartGoodId = new CartGoodId();
			cartGoodId.setCart(cart);
			cartGoodId.setGood(good);
			
			CartGood cartGood = cartGoodDAO.getById(cartGoodId);
			cartGood.setGoodAmount(goodDTO.getGoodAmount());
			cartGoodDAO.update(cartGood);
		}
		
		return SUCCESS;
	}
	
	public int deleteItem(CartItemDTO cartItem) {
		if (cartItem == null)
			return INVALID_ARGUMENT;
		
		Cart cart = new CartDAOImpl().getById(cartItem.getCartId());
		Good good = new GoodDAOImpl().getById(cartItem.getItemId());
		
		CartGoodId cartGoodId = new CartGoodId();
		cartGoodId.setCart(cart);
		cartGoodId.setGood(good);
		
		CartGoodDAOImpl cartGoodDAO = new CartGoodDAOImpl();
		
		CartGood cartGood = cartGoodDAO.getById(cartGoodId);
		cartGoodDAO.delete(cartGood);
		
		return SUCCESS;
	}
	
	public float getTotalPrice(String cartId) {
		if(cartId == null)
			return INVALID_ARGUMENT;
		
		System.out.println("cartId = " + cartId);
		
		IDAO<Cart> cartDAO = new CartDAOImpl();
		Cart cart = cartDAO.getById(cartId); 
		
		if(cart != null)
			return cart.getTotalPrice();
		else {
			System.out.println("cart is null");
			return 0;
		}
			
	}
	
	public void calculateTotalPrice(User user, String cartId) {
		if(cartId == null)
			return;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		
		Cart cart = new CartService().getCartOrCreateIfNotExist(user, session);
		
		float newTotalPrice = 0;
		List<CartGood> cartGoods = cart.getCartGoods();
		
		for (CartGood cartGood : cartGoods) {
			newTotalPrice += cartGood.getGood().getPrice() * cartGood.getGoodAmount();
		}
		
		cart.setTotalPrice(newTotalPrice);
		session.update(cart);
		
		transaction.commit();
		session.close();
	}
}
