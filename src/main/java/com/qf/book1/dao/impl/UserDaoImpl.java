package com.qf.book1.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.qf.book1.dao.UserDao;
import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.Record;
import com.qf.book1.pojo.User;

//实现一个接口，必须实现接口中的所有方法
@Repository
public class UserDaoImpl implements UserDao{
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public User login(String userName, String userPassword) {
		// TODO Auto-generated method stub
		
		String hql="from User u where u.userName=:userName and u.userPassword=:userPassword";
		return sessionFactory.getCurrentSession().createQuery(hql,User.class).setParameter("userName", userName)
		.setParameter("userPassword", userPassword).uniqueResult();
	}

	@Override
	public User findByName(String userName) {
		// TODO Auto-generated method stub
		String hql="from User u where u.userName=:userName";
		return sessionFactory.getCurrentSession().createQuery(hql,User.class).setParameter("userName", userName).uniqueResult();
	}
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		String hql="from User u order by u.id";
		return sessionFactory.getCurrentSession().createQuery(hql,User.class).getResultList();
	}

	@Override
	public List<User> findByLike(String userName) {
		// TODO Auto-generated method stub
		String hql="from User r where r.userName like :userName";
		return sessionFactory.getCurrentSession().createQuery(hql,User.class).setParameter("userName", "%"+userName+"%").getResultList();
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		 sessionFactory.getCurrentSession().persist(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(user);;
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		String hql="from User u where u.id=:id";
		return sessionFactory.getCurrentSession().createQuery(hql,User.class).setParameter("id",id).uniqueResult();
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public void updatePassWord(String oldPassWord, String oldPassWord1, String newPassWord,HttpServletRequest request) {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("u");
//		Md5Hash psw=new Md5Hash(newPassWord);
		user.setUserPassword(newPassWord);
		
		sessionFactory.getCurrentSession().merge(user);
	}		
}
