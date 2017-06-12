package org.school.tool.management.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.school.tool.management.model.LoginModel;
import org.school.tool.management.model.LogoutRequestModel;
import org.school.tool.management.model.LogoutResponseModel;
import org.school.tool.management.model.ResultModel;
import org.school.tool.management.model.RoleTypeModel;

public class LogoutServices {
	
	public LogoutResponseModel userLogout(LogoutRequestModel request){
		
		Configuration con = new Configuration().configure().addAnnotatedClass(LoginModel.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
//		Query loginQuery = session.createQuery("from LoginModel where userId =:userId");
//		loginQuery.setParameter("userId", request.getId());
//		LoginModel model = (LoginModel) loginQuery.uniqueResult();
		
		Session loginSession = sf.openSession();
		Transaction loginTx = loginSession.beginTransaction();
		Query query = loginSession.createQuery("update LoginModel set logged_in=:logged where userId =:userId");
		query.setParameter("logged", false);
		query.setParameter("userId", request.getId());
		query.executeUpdate();
		loginTx.commit();
		loginSession.close();
		
		LogoutResponseModel response = new LogoutResponseModel();
		ResultModel result = new ResultModel();
		result.setStatus("OK");
		result.setMessage("User logged out successfully!");
		response.setResult(result);
		
		return response;
	}

}
