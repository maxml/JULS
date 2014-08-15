package com.juls.persist;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.Good;

public class GoodDAOImpl implements IDAO<Good>{

	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	public List<Good> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Good> resultList;
		Transaction tr = session.beginTransaction();
		Query selectQuery = session.createQuery("from Good");
		resultList = selectQuery.list();
		tr.commit();
		return resultList;
	}

	public boolean insert(Good value) {
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

	public boolean update(Good value) {
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

	public boolean delete(Good value) {
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

	public Good getById(String id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Good resultGood = (Good) session.get(Good.class, id);
		tr.commit();
		return resultGood;
	}

}
