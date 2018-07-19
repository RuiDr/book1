package com.qf.book1.dao;

import java.util.List;

import com.qf.book1.pojo.Kind;

public interface KindDao {

	List<Kind> findAll();

	void addKind(Kind kind);

	Kind findByType(String type);

	void updateKind(Kind kind);

	Kind findById(String kId);

	void deleteKind(Kind kind);
	
}
