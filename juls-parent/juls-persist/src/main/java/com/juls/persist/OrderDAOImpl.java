package com.juls.persist;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.Order;
import com.juls.model.Order2;

public class OrderDAOImpl implements IDAO<Order2>{

	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	public List<Order2> getAll() {

		return null;
	}

	public Order2 getById(String id) {
		throw new UnsupportedOperationException();
	}

	public boolean insert(Order2 value) {
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

	public boolean update(Order2 value) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(Order2 value) {
		throw new UnsupportedOperationException();
	}

}
