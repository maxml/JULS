package com.juls.persist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.CartGood;
import com.juls.model.CartGoodId;

public class CartGoodDAOImpl implements IDAO<CartGood>{

	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	public List<CartGood> getAll() {
		throw new UnsupportedOperationException();
	}

	public CartGood getById(String id) {
		throw new UnsupportedOperationException();
	}
	
	public CartGood getById(CartGoodId id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		CartGood cartGood = (CartGood) session.get(CartGood.class, id);
		
		transaction.commit();
		session.close();
		
		return cartGood;
	}

	public boolean insert(CartGood value) {
		throw new UnsupportedOperationException();
	}

	public boolean update(CartGood value) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.update(value);
		
		transaction.commit();
		session.close();
		
		return true;
	}

	public boolean delete(CartGood value) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		session.delete(value);
		
		transaction.commit();
		session.close();
		
		return true;
	}
	
}
