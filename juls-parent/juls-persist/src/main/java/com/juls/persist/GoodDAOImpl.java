package com.juls.persist;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.juls.model.Good;

public class GoodDAOImpl implements IDAO<Good>{

	private static SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
	
	@SuppressWarnings("unchecked")
	public List<Good> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Good> resultList;
		Transaction tr = session.beginTransaction();
		Query selectQuery = session.createQuery("from Good");
		resultList = selectQuery.list();
		tr.commit();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Good> getAllByCategory(String category) {
		Session session = sessionFactory.getCurrentSession();
		List<Good> resultList;
		Transaction tr = session.beginTransaction();
		Query selectQuery = session.createQuery("from Good WHERE type='" + category + "'");
		resultList = selectQuery.list();
		tr.commit();
		return resultList;
	}

	public boolean insert(Good value) {
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

	public boolean update(Good value) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		try{
			session.update(value);
			tr.commit();
			return true;
		}
		catch(Exception ex){
			//tr.rollback();
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
			//tr.rollback();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Good> getAllSortedBy(String byWhat, String direction){
		Session session = sessionFactory.getCurrentSession();
		List<Good> resultList;
		Transaction tr = session.beginTransaction();
		Query selectQuery = session.createQuery("from Good ORDER BY " + byWhat + " " + direction );
		resultList = selectQuery.list();
		tr.commit();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Good> getAllFromCategorySortedBy(String category, String byWhat, String direction){
		Session session = sessionFactory.getCurrentSession();
		List<Good> resultList;
		Transaction tr = session.beginTransaction();
		Query selectQuery = session.createQuery("from Good WHERE type='" + category + "' ORDER BY " + byWhat + " " + direction );
		resultList = selectQuery.list();
		tr.commit();
		return resultList;
	}
	
	public Good getById(String id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction tr = session.beginTransaction();
		Good resultGood = (Good) session.get(Good.class, id);
		tr.commit();
		return resultGood;
	}

}
