package com.juls.persist;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.Order;
import com.juls.model.User;


/**
 * DAO for Order Entity;
 * Implements IDAO;
 * @version 1.0 SNAPSHOT 30.09.2014
 * @author Matvey Mitnitskyi
 * 
 */

public class OrderDAOImpl implements IDAO<Order> {
	
	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	 
	@SuppressWarnings("unchecked")
	public List<Order> getAll() {
		
		Session session = sessionFactory.getCurrentSession();
		List<Order> resultList;
		Transaction tr = session.beginTransaction();
		Query selectQuery = session.createQuery("from Order");
		resultList = selectQuery.list();
		tr.commit();
		
		return resultList;
	}

	public Order getById(String id) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Order resultUser = (Order) session.get(Order.class, id);
		tr.commit();
		
		return resultUser;
	}

	public boolean insert(Order value) {	
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		try{
			session.save(value);
			tr.commit();
			return true;
		}
		catch(Exception ex){
			tr.rollback();
			return false;
		}
	}

	public boolean update(Order value) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		try{
			session.update(value);
			if(!tr.wasCommitted())
			tr.commit();
			return true;
		}
		catch(Exception ex){
			tr.rollback();
			return false;
		}
	}

	public boolean delete(Order value) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try{
			session.delete(value);
			if(!tr.wasCommitted());
			tr.commit();
			return true;
		}
		catch(Exception ex){
			tr.rollback();
			return false;
		}
	}
	
	public Order getOrderByUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		
		String userID = user.getId();
		Query query = session.createQuery("from Order order "
				+ "WHERE user_id='"+userID+"'");
		Order resultOrder = null;
		try{
			resultOrder = (Order) query.list().get(0);
		}
		catch(Exception ex){
			System.err.println(ex.getMessage());
		}
		tr.commit();
		return resultOrder;
	}
	
	public Order getOrderByUser(User user, int status) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Order resultOrder = null; 
		try{
		String userID = user.getId();
		Query query = session.createQuery("from Order"
				+ " WHERE user_id='"+userID+"' and status ="+status+"");
		
			resultOrder = (Order) query.list().get(0);
			
			if(!tr.wasCommitted());
			tr.commit();
			return resultOrder;
		}
		catch(Exception ex){
			System.err.println(ex.getMessage());
			tr.rollback();			
			return resultOrder;
		}
		
	}
	
	public Order getOrderByNumber (String number) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from Order WHERE order_num='"+number+"'");
		Order resultOrder = null;
		try{
			resultOrder = (Order) query.list().get(0);
		}
		catch(Exception ex){
			System.err.println(ex.getMessage());
		}
		tr.commit();
		return resultOrder;
	}

}
