package org.school.tool.management.model;

import java.util.Date;

public class UserRequestModel {
	
	private enum roles {SuperUser, Teacher, Principal, Admin};
	
	private String firstName;
	private String lastName;
	private String gender;
	private String departments;
	private String fathersName;
	private String email;
	private String mobile;
	private String address;
	private Date dateOfJoining;
	private String salary;
	private boolean loginAvailable;
	private roles roleType;
	
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
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartments() {
		return departments;
	}
	public void setDepartments(String departments) {
		this.departments = departments;
	}
	public String getFathersName() {
		return fathersName;
	}
	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public boolean isLoginAvailable() {
		return loginAvailable;
	}
	public void setLoginAvailable(boolean loginAvailable) {
		this.loginAvailable = loginAvailable;
	}
	public roles getRoleType() {
		return roleType;
	}
	public void setRoleType(roles roleType) {
		this.roleType = roleType;
	}
	
	

}
