package com.qf.book1.service;

import java.util.List;

import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.Record;

public interface ReturnService {

	Message returnBook(String rId);

	List<Record> findAll();

	List<Record> findByLike(String userName);

}
