package com.qf.book1.dao.impl;




import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qf.book1.dao.BorrowDao;
import com.qf.book1.pojo.Record;
@Repository
public class BorrowDaoImpl implements BorrowDao{
	
@Resource
private SessionFactory sessionFactory;

@Override
public void borrow(Record record) {
	// TODO Auto-generated method stub
	sessionFactory.getCurrentSession().persist(record);
}


}
