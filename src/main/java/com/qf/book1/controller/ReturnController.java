package com.qf.book1.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.Record;
import com.qf.book1.service.ReturnService;
/**
 * 还书
 * @author 邓芮
 *
 */
@Controller
@RequestMapping("/returns")
public class ReturnController {
	@Resource
	private ReturnService returnService;
	
	@RequestMapping("/returnBook")
	@ResponseBody
	public Message returnBook(String rId){
		System.out.println("returnBook");
		try {
			return returnService.returnBook(rId);

		} catch (Exception e) {
			// TODO: handle exception
			Message msg=new Message();
			msg.setMsg("操作异常");
			return msg;
		}
	}
	@RequestMapping("/findAll")

	public ModelAndView findAll(){
		ModelAndView mav=new ModelAndView();
	    List<Record>records=returnService.findAll();
		mav.setViewName("/return.jsp");
		mav.addObject("records",records);
		return mav;
	}
	@RequestMapping("/findByLike")
	public ModelAndView findByLike(String userName){
		ModelAndView mav=new ModelAndView();
	    List<Record>records=returnService.findByLike(userName);
		mav.setViewName("/return.jsp");
		mav.addObject("records",records);
		return mav;
		
	}
}
