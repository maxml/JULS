package com.juls.persist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.User;

public class UserDAO {
	
	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	@Deprecated
	public boolean insert(){
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		User u = new User("test@mail.com", "12345");
		session.save(u);
		tr.commit();
		sessionFactory.close();
		return true;
	}
}
