package com.qf.book1.controller;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qf.book1.pojo.Book;
import com.qf.book1.pojo.Message;
import com.qf.book1.service.BookService;
import com.qf.book1.utils.UpUtils;
/**
 * @author 邓芮
 *
 */
@Controller
@RequestMapping("/books")
public class BookController {
	@Resource
	private BookService bookService;
	
	@RequestMapping("/findAll")
	public ModelAndView findAll() {
	     System.out.println("odswm[awmge,[kgpekwakpep");
		ModelAndView mav = new ModelAndView();
		List<Book> books = bookService.findAll();
		mav.addObject("books",books);
		mav.setViewName("/book.jsp");
		return mav;
	}
	
	@RequestMapping("/findByLike")
	@ResponseBody
	public ModelAndView findByLike(String bookName){
		ModelAndView mav=new ModelAndView();
		List<Book>books=bookService.findByLike(bookName);
		mav.setViewName("/book.jsp");
		mav.addObject("books",books);
		return mav;
	}
	
	@RequestMapping("/addBook")
	@ResponseBody
     public Message addBook(Book book,String type,MultipartFile filedata,HttpServletRequest request){
		System.out.println("/addBook"+book.toString());
		Message msg=new Message();
	   ModelAndView mav=new ModelAndView();
		try {
		   msg = bookService.addBook(book,type,filedata,request);
//		   mav.setViewName("/books/findAll");
//		   mav.addObject("msg",msg);
		   return msg;
		   } catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			msg.setMsg("操作失败");
//			mav.setViewName("/books/findAll");
//			mav.addObject("msg",msg);
			return msg;
		}
	}
//	@RequestMapping("/updateBook")
//	@ResponseBody
//	public ModelAndView updateBook(Book book,String type,MultipartFile filedata,HttpServletRequest request){
//		ModelAndView mav=new ModelAndView();
//		try {
//		   Message msg= bookService.updateBook(book,type,filedata,request);
//		   mav.setViewName("/books/findAll");
//		   mav.addObject("msg",msg);
//		   return mav;
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			Message msg=new Message();
//			msg.setMsg("操作失败");
//			mav.setViewName("/books/findAll");
//			mav.addObject("msg",msg);
//			return mav;
//		}
//	}
	@RequestMapping("/updateBook")
	@ResponseBody
	public Message updateBook(Book book,String type,MultipartFile filedata,HttpServletRequest request){
		Message msg=new Message();
		   try {
			    msg= bookService.updateBook(book,type,filedata,request);
			   return msg;
			    
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setMsg("更新失败");
			return msg;
		}
		
	}
	
	@RequestMapping("/deleteBook")
	@ResponseBody
	public Message deleteBook(String id){
		Message message=new Message();
		System.out.println("string id "+id);
		try {
			bookService.deleteBook(id);
			message.setMsg("删除成功");
			return message;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setMsg("删除异常");
			return message;
		}
	}
	@RequestMapping("/up")
	@ResponseBody
	public void up(MultipartFile filedata,HttpServletRequest request){
		String src=UpUtils.getSrc(filedata, request);
		System.out.println(src);
	}
	
}
