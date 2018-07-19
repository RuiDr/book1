package com.qf.book1.dao;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.User;

public interface UserDao {

	 User login(String userName, String userPassword);

	  User findByName(String userName);

	List<User> findAll();

	List<User> findByLike(String userName);

	void addUser(User user);

	void updateUser(User user);

	User findById(String id);

	void deleteUser(User user);

	void updatePassWord(String oldPassWord, String oldPassWord1, String newPassWord,HttpServletRequest request);
	
}
