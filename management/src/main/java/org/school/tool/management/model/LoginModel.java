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
	private String email;
	private String password;
	private String roleType;
	private String firstName;
	private String lastName;
	private int userId;
	private boolean remember;
	private boolean logged_in;
	
	
	public int getId() {
		return loginId;
	}
	public void setId(int id) {
		this.loginId = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	public boolean isLogged_in() {
		return logged_in;
	}
	public void setLogged_in(boolean logged_in) {
		this.logged_in = logged_in;
	}
}
