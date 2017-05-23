package org.school.tool.management.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Login")
public class LoginModel {
	
//	private enum roles {SuperUser, Teacher, Principal, Admin};
	
	@Id
	private int loginId;
	private String userName;
	private String password;
	private String roleType;
	
	public int getId() {
		return loginId;
	}
	public void setId(int id) {
		this.loginId = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String role) {
		this.roleType = role;
	}
	
	

}
