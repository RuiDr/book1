package com.qf.book1.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qf.book1.dao.BookDao;
import com.qf.book1.dao.BorrowDao;
import com.qf.book1.dao.UserDao;
import com.qf.book1.pojo.Book;
import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.Record;
import com.qf.book1.pojo.User;
import com.qf.book1.service.BorrowService;
@Service
// 添加事物
@Transactional
public class BorrowServiceImpl implements BorrowService {
	@Resource BorrowDao borrowDao;
	@Resource BookDao bookDao;
	@Resource UserDao userDao;

	@Override
	public Message borrow(Record record) {
		// TODO Auto-generated method stub
		Message msg=new Message();
		Book book = bookDao.findByName(record.getBookName());
		if(book!=null){
			User user=userDao.findByName(record.getUserName());
			if(user!=null){
				String id=UUID.randomUUID().toString().substring(0, 4);
				record.setId(id);
				Date date=new Date();
				SimpleDateFormat dFormat=new SimpleDateFormat("yyyy-mm-dd");
				dFormat.format(date);
				System.out.println("the date is "+date);
				record.setBorrowDate(date);
				System.out.println(record.toString());
				borrowDao.borrow(record);
				msg.setMsg("借书成功!");
				return msg;
			}else{
				msg.setMsg("请核对用户信息!");
				return msg;
			}
		}else{
			msg.setMsg("无该图书!");
			return msg;
		}
	}
}
