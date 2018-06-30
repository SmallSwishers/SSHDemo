package com.las.WebData.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.las.WebData.dao.UserDao;
import com.las.WebData.entity.Users;

/**
 * 
 * @author qiao
 * @version 1.0
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session ;
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public UserDaoImpl() {
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveUser(Users user) {
		// TODO Auto-generated method stub
		System.out.println("进入Dao save成功");
		System.out.println("sessionFactory : " + sessionFactory);
		session = sessionFactory.openSession();
		System.out.println("session : " + session);
		session.save(user);
	}

	@Override
	public void delUser(Users user) {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	@Override
	public void editUser(Users user) {
		// TODO Auto-generated method stub
		session = sessionFactory.getCurrentSession();
		session.update(user);
	}

	@Override
	public List<Users> getUser() {
		// TODO Auto-generated method stub
		List<Users> list = new ArrayList<Users>();
		String hql = "from Users";
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		list = session.createQuery(hql).list();
		trans.commit();
		return list;
	}

	@Override
	public Users getUser(int id) {
		// TODO Auto-generated method stub
		List<Users> list = new ArrayList<Users>();
		String hql = "from Users where id = ?";
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		list = session.createQuery(hql).setParameter(0, id).list();
		trans.commit();
		return list.get(0);
	}

	@Override
	public List<Users> getPageUser(int page, int size) {
		// TODO Auto-generated method stub
		List<Users> list = new ArrayList<Users>();
		String hql = "from Users";
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		
		Query query = session.createQuery(hql);
		if(page < 1){
			page = 1;
		}
		query.setFirstResult((page-1) * size);
		query.setMaxResults(size);
		list = query.list();
		trans.commit();
		return list;
	}

}
