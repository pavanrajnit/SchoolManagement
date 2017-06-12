package org.school.tool.management.model;

public class LoginResponseModel {
	
	private String session_token;
	private ResultModel result;
	private LoginResponseUserModel user;
	
	
	public String getSession_token() {
		return session_token;
	}
	public void setSession_token(String session_token) {
		this.session_token = session_token;
	}
	public ResultModel getResult() {
		return result;
	}
	public void setResult(ResultModel result) {
		this.result = result;
	}
	public LoginResponseUserModel getUser() {
		return user;
	}
	public void setUser(LoginResponseUserModel user) {
		this.user = user;
	}
}
