package org.school.tool.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserPermission")
public class RoleTypeModel {
	
	@Id
	private int id;
	private String name;
	private String permissions;
	@Column(name="userRoleAvailability")
	private String user_role_availability;
	private boolean is_default;
	private boolean login;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPermissions() {
		return permissions;
	}
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	public String getUser_role_availability() {
		return user_role_availability;
	}
	public void setUser_role_availability(String user_role_availability) {
		this.user_role_availability = user_role_availability;
	}
	public boolean isIs_default() {
		return is_default;
	}
	public void setIs_default(boolean is_default) {
		this.is_default = is_default;
	}
	public boolean isLogin() {
		return login;
	}
	public void setLogin(boolean login) {
		this.login = login;
	}
	
}
