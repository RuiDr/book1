package com.qf.book1.dao;

import java.util.List;

import com.qf.book1.pojo.Message;
import com.qf.book1.pojo.Record;

public interface ReturnDao {

	Record findById(String rId);

	Message delete(Record record);

	List<Record> findAll();

	List<Record> findByLike(String userName);

}
