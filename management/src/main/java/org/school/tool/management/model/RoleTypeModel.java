package org.school.tool.management.model;

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
	private String userRoleAvailability;
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
	public String getUserRoleAvailability() {
		return userRoleAvailability;
	}
	public void setUserRoleAvailability(String userRoleAvailability) {
		this.userRoleAvailability = userRoleAvailability;
	}

}
