package org.school.tool.management.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.eclipse.persistence.sessions.Login;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.school.tool.management.model.LoginModel;
import org.school.tool.management.model.RoleTypeModel;
import org.school.tool.management.model.SessionModel;

public class SessionCheckServices {
	
	public boolean checkSession(String token){
		
		Configuration con = new Configuration().configure().addAnnotatedClass(SessionModel.class).addAnnotatedClass(LoginModel.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query sessionQuery = session.createQuery("from SessionModel where sessionToken = :token");
		sessionQuery.setParameter("token", token);
		SessionModel sessionModel =  (SessionModel) sessionQuery.uniqueResult();
		
		Query loginQuery = session.createQuery("from LoginModel where loginId = :loginId");
		loginQuery.setParameter("loginId", sessionModel.getLoginId());
		LoginModel loginModel =  (LoginModel) loginQuery.uniqueResult();
		
		if(sessionModel != null) {
			Date currentDate = new Date();
			if(loginModel.isRemember()){
				if(currentDate.compareTo(sessionModel.getSession_timeout()) == -1){
					return true;
				}else {
					return false;
				}
			}else {
				if(currentDate.compareTo(sessionModel.getSession_timeout()) == -1){
					TimeZone indianTimeZone = TimeZone.getTimeZone("GMT+05:30");
					Calendar calendar = Calendar.getInstance(indianTimeZone);
					long time = calendar.getTimeInMillis();
					Date sessionTime = new Date(time + (5*60000));
					Query query = session.createQuery("update SessionModel set session_timeout=:timeout where sessionToken = :token");
					query.setParameter("timeout", sessionTime);
					query.setParameter("token", token);
					query.executeUpdate();
					tx.commit();
					session.close();
					return true;
				}else {
					return false;
				}	
			}
		}
		
		return false;
	}

}
