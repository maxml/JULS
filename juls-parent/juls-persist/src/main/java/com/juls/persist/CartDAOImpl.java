package com.juls.persist;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.Cart;

public class CartDAOImpl implements IDAO<Cart> {

	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	@SuppressWarnings("unchecked")
	public List<Cart> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Cart> resultList;
		Transaction tr = session.beginTransaction();
		Query selectQuery = session.createQuery("from Cart");
		resultList = selectQuery.list();
		tr.commit();
		
		return resultList;
	}

	public Cart getById(String id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Cart resultGood = (Cart) session.get(Cart.class, id);
		tr.commit();
		
		return resultGood;
	}

	public boolean insert(Cart value) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try{
			session.save(value);
			tr.commit();
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	public boolean update(Cart value) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Cart value) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try{
			session.delete(value);
			tr.commit();
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	

}
