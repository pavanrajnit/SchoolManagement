package org.school.tool.management.model;

public class LoginResponseModel {
	
	private LoginResposeResultModel result;
	private String[] permissions;
	private String roleType;
	private String sessiontoken;
	private String userRoleAvailability;
	public LoginResposeResultModel getResult() {
		return result;
	}
	public void setResult(LoginResposeResultModel result) {
		this.result = result;
	}
	public String[] getPermissions() {
		return permissions;
	}
	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	public String getSessiontoken() {
		return sessiontoken;
	}
	public void setSessiontoken(String sessiontoken) {
		this.sessiontoken = sessiontoken;
	}
	public String getUserRoleAvailability() {
		return userRoleAvailability;
	}
	public void setUserRoleAvailability(String userRoleAvailability) {
		this.userRoleAvailability = userRoleAvailability;
	}
	
	
}
