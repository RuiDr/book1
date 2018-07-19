package com.qf.book1.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.Record;
import com.qf.book1.service.BorrowService;
/**
 * 借书操作
 * @author 邓芮
 */
@Controller
@RequestMapping("/borrows")
public class BorrowController {
	@Resource 
	private BorrowService borrowService;
	
	@RequestMapping("/borrow")
	@ResponseBody
	public Message borrow(Record  record){
		Message msg=new Message();
		
		try {
			return borrowService.borrow(record);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setMsg("出现未知错误");
			return msg;
		}
	}
}
