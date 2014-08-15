package com.juls.persist;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.Good;
import com.juls.model.UserDetails;

public class UserDetailsDAOImpl implements IDAO<UserDetails>{

	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	public List<UserDetails> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<UserDetails> resultList;
		Transaction tr = session.beginTransaction();
		Query selectQuery = session.createQuery("from UserDetails");
		resultList = selectQuery.list();
		tr.commit();
		return resultList;
	}

	public boolean insert(UserDetails value) {
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

	public boolean update(UserDetails value) {
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

	public boolean delete(UserDetails value) {
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

	public UserDetails getById(String id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		UserDetails resultUserDetails = (UserDetails) session.get(UserDetails.class, id);
		tr.commit();
		return resultUserDetails;
	}

}
