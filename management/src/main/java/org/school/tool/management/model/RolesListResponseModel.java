package org.school.tool.management.model;

import java.util.List;

public class RolesListResponseModel {
	
	private ResultModel result;
	private List<RoleTypeModel> rolesList;
	
	public ResultModel getResult() {
		return result;
	}
	public void setResult(ResultModel result) {
		this.result = result;
	}
	public List<RoleTypeModel> getRolesList() {
		return rolesList;
	}
	public void setRolesList(List<RoleTypeModel> rolesList) {
		this.rolesList = rolesList;
	}

}
