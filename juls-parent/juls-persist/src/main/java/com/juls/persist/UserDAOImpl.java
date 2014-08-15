package com.juls.persist;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.User;

public class UserDAOImpl implements IDAO<User>{
	
	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	public List<User> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<User> resultList;
		Transaction tr = session.beginTransaction();
		Query selectQuery = session.createQuery("from User");
		resultList = selectQuery.list();
		tr.commit();
		return resultList;
	}

	public boolean insert(User value) {
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

	public boolean update(User value) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try{
			session.update(value);
			tr.commit();
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}

	public boolean delete(User value) {
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

	public User getById(String id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		User resultUser = (User) session.get(User.class, id);
		tr.commit();
		return resultUser;
	}
}
