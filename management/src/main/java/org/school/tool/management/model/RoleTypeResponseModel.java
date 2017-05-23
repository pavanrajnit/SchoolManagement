package org.school.tool.management.model;

public class RoleTypeResponseModel {
	
	private LoginResposeResultModel result;
	private RoleTypeRequestModel Role;
	public LoginResposeResultModel getResult() {
		return result;
	}
	public void setResult(LoginResposeResultModel result) {
		this.result = result;
	}
	public RoleTypeRequestModel getRole() {
		return Role;
	}
	public void setRole(RoleTypeRequestModel Role) {
		this.Role = Role;
	}

}
