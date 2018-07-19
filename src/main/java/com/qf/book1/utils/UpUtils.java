package com.qf.book1.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 * @author 邓芮
 *
 */
public class UpUtils {
	public static String getSrc(MultipartFile filedate,HttpServletRequest request){
		// 获取当前路径（项目部署在tomcat中的路径）
		String proPath = request.getSession().getServletContext().getRealPath("/");
//		获取工作区间的项目跟路径
		String relPath="D:\\eclipseWorkSpace\\book1\\src\\main\\webapp\\";
		// 设置文件上传后的保存路径
		String savePath="images/fileUp";
		// 声明文件路径并且创建该文件目录
		File file=new File(proPath+savePath);
		if(!file.exists()){
			file.mkdirs();
		}
		File file1=new File(relPath+savePath);
//		保证跟路径下的图片位置存在
		if(!file1.exists()){
			file1.mkdirs();
		}
		try {
//			获取上传文件的名称
			String orgName = filedate.getOriginalFilename();
			String end=orgName.substring(orgName.lastIndexOf("."));
		    String start=String.valueOf(System.currentTimeMillis());
			
			String imgPath=savePath+"/"+start+end;
//		    创建文件输出流
			FileOutputStream fos=new FileOutputStream(proPath+imgPath,true);
			FileOutputStream fos1=new FileOutputStream(relPath+imgPath,true);
			fos1.write(filedate.getBytes());
			fos.write(filedate.getBytes());
			fos.flush();
			fos1.flush();
			fos.close();
			fos1.close();
			return imgPath;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}
}
