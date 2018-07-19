package com.qf.book1.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qf.book1.dao.ReturnDao;
import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.Record;
import com.qf.book1.service.ReturnService;
@Service
@Transactional
public class ReturnServiceImpl implements ReturnService{
	@Resource
	private ReturnDao returnDao;

	@Override
	public Message returnBook(String rId) {
		// TODO Auto-generated method stub

		Message message=new Message();
		Record record=returnDao.findById(rId);
		if(record!=null){
			returnDao.delete(record);
			message.setMsg("还书成功");
			return message;
		}else{
			message.setMsg("无该记录，请刷新页面");
			return message;
		}
	}

	@Override
	public List<Record> findAll() {
		// TODO Auto-generated method stub
		return returnDao.findAll();
		
	}

	@Override
	public List<Record> findByLike(String userName) {
		// TODO Auto-generated method stub
		return returnDao.findByLike(userName);
	}
}
