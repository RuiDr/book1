package com.qf.book1.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 图书种类
 * @author 邓芮
 *
 */
@Entity
@Table(name="kind")
public class Kind implements Serializable{

	private static final long serialVersionUID = 6808812173975202327L;

	@Id
	@Column(length=11)
	private String id;

	@Column(length=20)
	private String type;
	
	@OneToMany(mappedBy="kind")
	private List<Book> books;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "Kind [id=" + id + ", type=" + type + ", books=" + books + "]";
	}
}
