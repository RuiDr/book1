package com.qf.book1.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.qf.book1.pojo.Book;
import com.qf.book1.pojo.Message;

public interface BookService {

	List<Book> findAll();

	List<Book> findByLike(String bookName);

	Message addBook(Book book, String type, MultipartFile filedata, HttpServletRequest request);

	Message updateBook(Book book, String type,MultipartFile filedata, HttpServletRequest request);

	void deleteBook(String id);
	
}
