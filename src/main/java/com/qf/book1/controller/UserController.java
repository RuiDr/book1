package com.qf.book1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.tools.mail.MailMessage;
import org.apache.xml.serializer.utils.MsgKey;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.User;
import com.qf.book1.service.UserService;

@Controller
//地址
@RequestMapping("/users")
public class UserController {
//	实现自动装配，生成代理
//	声明service的对象
	@Resource
	private UserService userService;
	
//	对应login name
	@RequestMapping("/login")
//	返回视图和路径,默认返回方式转发
	public ModelAndView login(User user,HttpServletRequest request){
		System.out.println("/users/login");
//		验证用户
		ModelAndView mav=new ModelAndView();
		User u=userService.login(user);
		if(u!=null){
// 重定向 mav.setViewName("redirect:/index.jsp");
			request.getSession().setAttribute("u", u);
			mav.setViewName("/index.jsp");
			return mav;
		}else{
			mav.addObject("msg", "用户名或者密码错误");
			mav.setViewName("/login.jsp");
			return mav;
		}	
	}
	@RequestMapping("/findAll")
	public ModelAndView findAll(){
		ModelAndView mav=new ModelAndView();
	List<User>users=userService.findAll();
	mav.setViewName("/userList.jsp");
	mav.addObject("users",users);
	return mav;
	}
	
	@RequestMapping("/findByLike")
	public ModelAndView findByLike(String userName){
		ModelAndView mav=new ModelAndView();
		List<User>users=userService.findByLike(userName);
		mav.setViewName("/userList.jsp");
		mav.addObject("users",users);
		return mav;
	}
	@RequestMapping("/addUser")
	@ResponseBody
	public Message addUser(User user){
		try {
			return userService.addUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			Message message=new Message();
			message.setMsg("添加出现异常");
			return message;
		}
	}
	@RequestMapping("/updateUser")
	@ResponseBody
	public Message updateUser(User user){
		Message message=new Message();
		try {
			return userService.updateUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setMsg("更新异常,用户名重复");
			return message;
		}
	}
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Message deleteUser(String uId){
		Message message=new Message();
		  try {
			  return userService.deleteUser(uId);
		} catch (Exception e) {
			// TODO: handle exception
			message.setMsg("删除失败");
			e.printStackTrace();
			return message;
		}
	}
	@RequestMapping("/updatePassWord")
	@ResponseBody
	public Message updatePassWord(String oldPassWord,String oldPassWord1,String newPassWord,HttpServletRequest request){
		Message message=new Message();
		Md5Hash psw=new Md5Hash(oldPassWord);
		Md5Hash psw1=new Md5Hash(oldPassWord1);
		Md5Hash psw2=new Md5Hash(newPassWord);
		try {
			return  userService.updatePassWord(psw.toString(),psw1.toString(),psw2.toString(),request);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setMsg("修改密码异常");
			return message;
		}
	}
	
	@RequestMapping("/updateOrgPassWord")
	@ResponseBody
	public Message updateOrgPassWord(String oldPassWord,String oldPassWord1,String newPassWord,HttpServletRequest request){
		Message message=new Message();
		System.out.println("isdofemoiamngiejfa0png");
		Md5Hash psw=new Md5Hash(oldPassWord);
		System.out.println(psw.toString());
		User user = (User) request.getSession().getAttribute("u");
		try {
			if(user.getUserPassword().equals(psw.toString())){
				message.setMsg("原密码正确");
				return message;
			}else{
				message.setMsg("原密码错误");
				return message;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message.setMsg("修改密码异常");
			return message;
		}
	}
	
	@RequestMapping("/upUser")
	@ResponseBody
	public Message upUser(MultipartFile filedata,HttpServletRequest request){
		Message msg=new Message();
		//声明模板对象 先设置为空 目的为了判断excel的版本  2003班后缀为.xls 新版为.xlsx
		Workbook book=null;
		try {
//			判断是否上传了文件
			if (filedata.getSize()==0) {
				msg.setMsg("请选择上传文件");
				return msg;
			}
//			判断excel版本
			if (filedata.getOriginalFilename().endsWith(".xlsx")) {
				book=new XSSFWorkbook(filedata.getInputStream());
				
				
			}else{
				book=new HSSFWorkbook(filedata.getInputStream());
			}
//			获取sheet表格
			Sheet sheet = book.getSheetAt(0);
//			判断文件sheet文本中是否有数据
			if(sheet.getLastRowNum()<1){
				msg.setMsg("无数据，请核对文件");
				return msg;
			}
//			遍历sheer中的每一行
			List<User>users=new  ArrayList<>();
			for(int i=1;i<sheet.getLastRowNum();i++){
				Row row = sheet.getRow(i);
				User  user=new User();
				user.setUserName(row.getCell(0).getStringCellValue());
				user.setEmail(row.getCell(1).getStringCellValue());
				user.setPhone(row.getCell(2).getStringCellValue());
				users.add(user);
			}
			userService.addUser(users);
			msg.setMsg("success");
			return msg;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setMsg("存在已有用户");
			return msg;
		}
	}
}
