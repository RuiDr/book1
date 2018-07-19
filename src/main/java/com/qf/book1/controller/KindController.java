package com.qf.book1.controller;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qf.book1.pojo.Kind;
import com.qf.book1.pojo.Message;
import com.qf.book1.service.KindService;

@Controller
@RequestMapping("/kinds")
public class KindController {
	
	@Resource
	private KindService kindService;
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<Kind> kinds = kindService.findAll();
		mav.addObject("kinds",kinds);
		mav.setViewName("/bookKind.jsp");
		return mav;
	}
	
	@RequestMapping("/addKind")
	@ResponseBody
	public Message addKind(Kind kind) {
		try {
			return kindService.addKind(kind);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("添加失败");
			return  msg;
		}
	}
	
	@RequestMapping("/updateKind")
	@ResponseBody
	public  Message updateKind(Kind kind) {
		try {
			Message message = kindService.updateKind(kind);
			return message;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("修改失败");
			return msg;
		}
	}
	
	@RequestMapping("/deleteKind")
	@ResponseBody
	public Message deleteKind(String kId) {
		try {
			return  kindService.deleteKind(kId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message msg = new Message();
			msg.setMsg("删除失败");
			return msg;
		}
	}
}
