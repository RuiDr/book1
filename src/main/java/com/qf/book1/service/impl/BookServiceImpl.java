package com.qf.book1.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.qf.book1.dao.BookDao;
import com.qf.book1.dao.KindDao;
import com.qf.book1.pojo.Book;
import com.qf.book1.pojo.Kind;
import com.qf.book1.pojo.Message;
import com.qf.book1.service.BookService;
import com.qf.book1.utils.UpUtils;
@Service
@Transactional
public class BookServiceImpl implements BookService {
	@Resource
	private BookDao bookDao;
	@Resource KindDao kindDao;

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return bookDao.findAll();
	}
	@Override
	public List<Book> findByLike(String bookName) {
		// TODO Auto-generated method stub
		return bookDao.findByLike(bookName);
	}

	@Override
	public Message addBook(Book book, String type,MultipartFile filedata,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Book b=bookDao.findById(book.getId());
		Message msg=new Message();
		if(b==null){
			Kind kind=kindDao.findByType(type);
			if(kind==null){
//				如果为空
				Kind k=new Kind();
				String kId=UUID.randomUUID().toString().substring(0, 4);
				k.setId(kId);
				k.setType(type);
				kindDao.addKind(k);
				book.setKind(k);
			}else{
				book.setKind(kind);
			}
//			判断上传文件
			if (filedata.getSize()>0) {
				String src = UpUtils.getSrc(filedata, request);
				book.setAddress(src);	
			}else{
				book.setAddress("");
			}
			String bId=UUID.randomUUID().toString().substring(0, 4);
			book.setId(bId);
			bookDao.addBook(book);
			msg.setMsg("添加成功");
			System.out.println(msg.toString());
			return msg;

		}else{
			msg.setMsg("图书已存在");
			return msg;
			
		}
	}
	@Override
	public Message updateBook(Book book, String type,MultipartFile filedata,HttpServletRequest request) {
		// TODO Auto-generated method stu
		Message msg=new Message();
		System.out.println("bookId"+book.getId());
		Book b = bookDao.findById(book.getId());
		if(b!=null){
			Kind kind=kindDao.findByType(type);
			if(kind==null){
				Kind k=new Kind();
				String kId=UUID.randomUUID().toString().substring(0, 4);
				k.setId(kId);
				k.setType(type);
				kindDao.addKind(k);
				book.setKind(k);				
			}else{
				book.setKind(kind);
			}
			if(filedata.getSize()>0){
				String src=UpUtils.getSrc(filedata, request);
				book.setAddress(src);
			}else{
				book.setAddress(b.getAddress());
			}
			bookDao.updateBook(book);
			msg.setMsg("修改成功");
			return msg;

		}else{
			msg.setMsg("该对象不存在");
			return msg;
		}
	}
	@Override
	public void deleteBook(String id) {
		// TODO Auto-generated method stub
	 Book book=bookDao.findById(id);
     bookDao.deleteBook(book);
	}
}
