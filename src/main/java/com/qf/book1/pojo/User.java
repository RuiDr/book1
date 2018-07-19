package com.qf.book1.pojo;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 用户实体类
 * @author 邓芮
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = -4859716920255943152L;

//	数据库中生成主键
	@Id
//	设置数据库中的长度
	@Column(length=11)
	private String  id;
	
//	设置数据库中的长度
	@Column(length=20)
	private String userName;
	
//	设置数据库中的长度
	@Column(length=20)
	private String userPassword;
	
//	设置数据库中的长度
	@Column(length=11)
	private String phone;
	
//	设置数据库中的长度
	@Column(length=20)
	private String email;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword=" + userPassword + ", phone=" + phone
				+ ", email=" + email + "]";
	}
}
