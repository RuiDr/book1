package com.qf.book1.service;

import java.util.List;

import com.qf.book1.pojo.Kind;
import com.qf.book1.pojo.Message;

public interface KindService {

	List<Kind> findAll();
	Message addKind(Kind kind);
	Message updateKind(Kind kind);
	Message deleteKind(String kId);

	
}
