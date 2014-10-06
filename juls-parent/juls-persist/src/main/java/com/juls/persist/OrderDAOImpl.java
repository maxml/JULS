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

	public boolean insert( Order value) {	
		Session session = sessionFactory.getCurrentSession();
		Transaction tr;
		if (session.getTransaction() == null)
			tr = session.beginTransaction();
		else
			tr = session.getTransaction();
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
		Transaction tr;
		if (session.getTransaction() == null)
			tr = session.beginTransaction();
		else 
			tr = session.getTransaction();	
		try{
			session.update(value);
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
		Transaction tr;
		if (session.getTransaction() == null)
			tr = session.beginTransaction();
		else 
			tr = session.getTransaction();
		try{
			session.delete(value);
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
		Query query = session.createQuery("from Order order WHERE order.user='"+user+"'");
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
	
	public Order getOrderByNumber (String number) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Query query = session.createQuery("from Order order WHERE order.order_num='"+number+"'");
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
