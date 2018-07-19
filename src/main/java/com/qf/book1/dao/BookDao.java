package com.qf.book1.dao;

import java.util.List;

import com.qf.book1.pojo.Book;

public interface BookDao {
	List<Book> findByKId(String kId);
	void updateBook(Book book);
	List<Book> findAll();
	List<Book> findByLike(String bookName);
	Book findByName(String bookName);
	void addBook(Book book);
	Book findById(String id);
	void deleteBook(Book book);
}
