package org.school.tool.management.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="SessionTable")
public class SessionModel {
	
	@Id 
	private int sessionId;
	private String sessionToken;
	private int loginId;
	private Date session_timeout;
	
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}
	public Date getSession_timeout() {
		return session_timeout;
	}
	public void setSession_timeout(Date session_timeout) {
		this.session_timeout = session_timeout;
	}

}
