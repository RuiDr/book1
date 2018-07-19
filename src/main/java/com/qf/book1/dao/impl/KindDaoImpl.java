package com.qf.book1.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qf.book1.dao.KindDao;
import com.qf.book1.pojo.Kind;
/**
 * 图书种类数据操作层
 * @author 邓芮
 */
@Repository
public class KindDaoImpl implements KindDao{
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Kind> findAll() {
		// TODO Auto-generated method stub
		String hql="from Kind k order by k.id";
//		类反射
		return  sessionFactory.getCurrentSession().createQuery(hql,Kind.class).getResultList();
		
	}

	@Override
	public void addKind(Kind kind) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(kind);
	}
	
	@Override
	public Kind findByType(String type) {
		// TODO Auto-generated method stub
		String hql="from Kind k where k.type=:type";
		return sessionFactory.getCurrentSession().createQuery(hql,Kind.class).setParameter("type", type).uniqueResult();
		
	}
//	了解
	@Override
	public void updateKind(Kind kind) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(kind);
	}

	@Override
	public Kind findById(String kId) {
		// TODO Auto-generated method stub
		String hql="from Kind k where k.id=:kId";
		return sessionFactory.getCurrentSession().createQuery(hql,Kind.class).setParameter("kId", kId).uniqueResult();

	}
	@Override
	public void deleteKind(Kind kind) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(kind);
	}
}
