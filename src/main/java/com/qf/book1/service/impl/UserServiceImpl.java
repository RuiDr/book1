package com.qf.book1.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.loader.plan.exec.process.spi.ReturnReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qf.book1.dao.UserDao;
import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.User;
import com.qf.book1.service.UserService;

//服务层
@Service
@Transactional
public class UserServiceImpl implements UserService {
//声明接口对象
	@Resource
	private UserDao userDao;

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		Md5Hash psw=new Md5Hash(user.getUserPassword());
//		user.setUserPassword(psw.toString());
	user.setUserPassword(psw.toString());
		System.out.println(user.getUserPassword());
		return userDao.login(user.getUserName(),user.getUserPassword());
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	@Override
	public List<User> findByLike(String userName) {
		// TODO Auto-generated method stub
		return userDao.findByLike(userName);
	}

	@Override
	public Message addUser(User user) {
		// TODO Auto-generated method stub
		Message msg=new Message();

		User user2 = userDao.findByName(user.getUserName());
		if(user2==null){
			String id=UUID.randomUUID().toString().substring(0, 4);
			user.setId(id);
             Md5Hash md5Hash=new Md5Hash("123");			
			user.setUserPassword(md5Hash.toString());			
			userDao.addUser(user);
			msg.setMsg("添加成功");
			return msg;
		}else{
			msg.setMsg("用户已存在");
			return msg;
		}
		
	}
	@Override
	public Message updateUser(User user) {
		// TODO Auto-generated method stub
		User user2 = userDao.findById(user.getId());
	
		Message msg=new Message();
		
		
		if(user2!=null){
			 userDao.updateUser(user);
				
			 msg.setMsg("修改成功");
		     return msg;
		}else{
			msg.setMsg("用户不存在");
			return msg;
		}
	}

	@Override
	public Message deleteUser(String uId) {
		// TODO Auto-generated method stub
		User user = userDao.findById(uId);
		Message msg=new Message();
		if(user!=null){
			userDao.deleteUser(user);
			msg.setMsg("删除成功");
			return msg;
			
		}else{
			msg.setMsg("该用户已被删除，请刷新");
			return msg;
		}
		
	
	}

	@Override
	public Message updatePassWord(String oldPassWord, String oldPassWord1, String newPassWord,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Message message=new Message();
		User user = (User) request.getSession().getAttribute("u");
		if(oldPassWord.equals(user.getUserPassword())){
			if(oldPassWord1.equals(newPassWord)){
				
			}else{
				message.setMsg("两次密码输入不一致");
				return message;
			}
			userDao.updatePassWord(oldPassWord,oldPassWord1,newPassWord,request);
			message.setMsg("修改密码成功");
			return message;
		}else {
			message.setMsg("旧密码输入错误");
			return message;
		}
		
	}

	@Override
	public void addUser(List<User> users) {
		// TODO Auto-generated method stub
		for (User user : users) {
			String idString=UUID.randomUUID().toString().substring(0, 4);
			
			String pString="123";
			Md5Hash md5Hash=new Md5Hash(pString);
			
			user.setUserPassword(md5Hash.toString());
			user.setId(idString);
			userDao.addUser(user);
			}
	}


}
