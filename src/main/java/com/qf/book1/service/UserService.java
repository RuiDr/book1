package com.qf.book1.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.User;

public interface UserService {

	User login(User user);

	List<User> findAll();

	List<User> findByLike(String userName);

	Message addUser(User user);

	Message updateUser(User user);


	Message deleteUser(String uId);

	Message updatePassWord(String oldPassWord, String oldPassWord1, String newPassWord,HttpServletRequest request);

	void addUser(List<User> users);

}
