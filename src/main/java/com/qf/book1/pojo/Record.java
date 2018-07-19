package com.qf.book1.pojo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author 邓芮
 *
 */
@Entity
@Table(name="record")
public class Record implements Serializable{
	
	private static final long serialVersionUID = 3719551325692952435L;

	@Id
	@Column(length=11)
	private String id ;
	
	@Column(length=20)
	private String bookName;
	
	@Column(length=20)
	private String userName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date borrowDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date returnDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	@Override
	public String toString() {
		return "Record [id=" + id + ", bookName=" + bookName + ", userName=" + userName + ", borrowDate=" + borrowDate
				+ ", returnDate=" + returnDate + "]";
	}
}
