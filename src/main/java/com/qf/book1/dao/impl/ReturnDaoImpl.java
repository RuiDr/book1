package com.qf.book1.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qf.book1.dao.ReturnDao;
import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.Record;

@Repository
public class ReturnDaoImpl implements ReturnDao{
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public Record findById(String rId) {
		// TODO Auto-generated method stub
		String hql="from Record r where r.id=:rId";
		return sessionFactory.getCurrentSession().createQuery(hql,Record.class).setParameter("rId", rId).uniqueResult();
		 
	}

	@Override
	public Message delete(Record record) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(record);
		return null;
	}

	@Override
	public List<Record> findAll() {
		// TODO Auto-generated method stub
		String hql="from Record r order by r.id";
		return sessionFactory.getCurrentSession().createQuery(hql,Record.class).getResultList();
	}
	@Override
	public List<Record> findByLike(String userName) {
		System.out.println("odsavpekwpg"+userName);
		// TODO Auto-generated method stub
		String hql="from Record r where r.userName like :userName";
		return sessionFactory.getCurrentSession().createQuery(hql,Record.class).setParameter("userName", "%"+userName+"%").getResultList();
	}
}
