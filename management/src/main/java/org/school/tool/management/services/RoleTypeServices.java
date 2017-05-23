package org.school.tool.management.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.school.tool.management.model.LoginResposeResultModel;
import org.school.tool.management.model.RoleTypeModel;
import org.school.tool.management.model.RoleTypeRequestModel;
import org.school.tool.management.model.RoleTypeResponseModel;

public class RoleTypeServices {
	
	public RoleTypeResponseModel createRole(RoleTypeRequestModel request){

		RoleTypeModel roleTypeModel = new RoleTypeModel();
		roleTypeModel.setId(0);
		roleTypeModel.setName(request.getName());
		String[] requestPermissions = request.getPermissions();
		StringBuilder permissions = new StringBuilder();
		for(int i=0; i<requestPermissions.length; i++){
			if(i==requestPermissions.length-1){
				permissions.append(requestPermissions[i]);	
			}else {
				permissions.append(requestPermissions[i] + ", ");
			}
		}
		roleTypeModel.setPermissions(permissions.toString());
		roleTypeModel.setUserRoleAvailability(request.getUserRoleAvailability());
		
		Configuration con = new Configuration().configure().addAnnotatedClass(RoleTypeModel.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(roleTypeModel);
		tx.commit();
		session.close();
		
		RoleTypeResponseModel responseModel = new RoleTypeResponseModel();
		LoginResposeResultModel result = new LoginResposeResultModel();
		result.setStatus("Ok");
		result.setMessage("Added!");
		responseModel.setResult(result);
		responseModel.setRole(request);
		
		return responseModel;
	}
	
	public RoleTypeResponseModel updateRole(RoleTypeRequestModel request){

		RoleTypeModel roleTypeModel = new RoleTypeModel();
		roleTypeModel.setId(0);
		roleTypeModel.setName(request.getName());
		String[] requestPermissions = request.getPermissions();
		StringBuilder permissions = new StringBuilder();
		for(int i=0; i<requestPermissions.length; i++){
			if(i==requestPermissions.length-1){
				permissions.append(requestPermissions[i]);	
			}else {
				permissions.append(requestPermissions[i] + ", ");
			}
		}
		roleTypeModel.setPermissions(permissions.toString());
		roleTypeModel.setUserRoleAvailability(request.getUserRoleAvailability());
		
		Configuration con = new Configuration().configure().addAnnotatedClass(RoleTypeModel.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query permissionQuery = session.createQuery("update RoleTypeModel set permissions=:per,userRoleAvailability=:userRole where name = :n");
		permissionQuery.setParameter("per", roleTypeModel.getPermissions());
		permissionQuery.setParameter("userRole", roleTypeModel.getUserRoleAvailability());
		permissionQuery.setParameter("n", request.getName());
		permissionQuery.executeUpdate();
		
		tx.commit();
		session.close();
		
		RoleTypeResponseModel responseModel = new RoleTypeResponseModel();
		LoginResposeResultModel result = new LoginResposeResultModel();
		result.setStatus("Ok");
		result.setMessage("Update!");
		responseModel.setResult(result);
		responseModel.setRole(request);
		
		return responseModel;
	}

}
