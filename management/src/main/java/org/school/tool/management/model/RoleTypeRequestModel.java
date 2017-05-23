package org.school.tool.management.model;

public class RoleTypeRequestModel {
	
	private String name;
	private String[] permissions;
	private String userRoleAvailability;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getPermissions() {
		return permissions;
	}
	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
	public String getUserRoleAvailability() {
		return userRoleAvailability;
	}
	public void setUserRoleAvailability(String userRoleAvailability) {
		this.userRoleAvailability = userRoleAvailability;
	}

}
