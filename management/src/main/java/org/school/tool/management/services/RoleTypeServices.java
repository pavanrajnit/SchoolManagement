package org.school.tool.management.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.school.tool.management.model.ResultModel;
import org.school.tool.management.model.RoleTypeModel;
import org.school.tool.management.model.RoleTypeRequestModel;
import org.school.tool.management.model.RoleTypeResponseModel;
import org.school.tool.management.model.RolesListResponseModel;

public class RoleTypeServices {
	
	public RoleTypeResponseModel createRole(RoleTypeRequestModel request){

		RoleTypeModel roleTypeModel = new RoleTypeModel();
		roleTypeModel.setId(0);
		roleTypeModel.setName(request.getName().toUpperCase());
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
		roleTypeModel.setUser_role_availability(request.getUser_role_availability());
		roleTypeModel.setIs_default(request.isIs_default());
		roleTypeModel.setLogin(request.isLogin());
		
		Configuration con = new Configuration().configure().addAnnotatedClass(RoleTypeModel.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Query permissionQuery = session.createQuery("from RoleTypeModel where name = :n");
		permissionQuery.setParameter("n", request.getName());
		RoleTypeModel model = (RoleTypeModel) permissionQuery.uniqueResult();
		
		if(model != null){
			RoleTypeResponseModel responseModel = new RoleTypeResponseModel();
			ResultModel result = new ResultModel();
			result.setStatus("Error");
			result.setMessage("Role Type already exist!");
			responseModel.setResult(result);
//			responseModel.setRole(request);
			
			return responseModel;
		}else {
			session.save(roleTypeModel);
		}
	
		tx.commit();
		session.close();
		
		RoleTypeResponseModel responseModel = new RoleTypeResponseModel();
		ResultModel result = new ResultModel();
		result.setStatus("Ok");
		result.setMessage("Added!");
		responseModel.setResult(result);
//		responseModel.setRole(request);
		
		return responseModel;
	}
	
	public RoleTypeResponseModel updateRole(RoleTypeRequestModel request){

		RoleTypeModel roleTypeModel = new RoleTypeModel();
		roleTypeModel.setId(0);
		roleTypeModel.setName(request.getName().toUpperCase());
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
		roleTypeModel.setUser_role_availability(request.getUser_role_availability());
		roleTypeModel.setIs_default(request.isIs_default());
		roleTypeModel.setLogin(request.isLogin());
		
		Configuration con = new Configuration().configure().addAnnotatedClass(RoleTypeModel.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query permissionQuery = session.createQuery("update RoleTypeModel set permissions=:per,userRoleAvailability=:userRole,login=:login where name = :n");
		permissionQuery.setParameter("per", roleTypeModel.getPermissions());
		permissionQuery.setParameter("userRole", roleTypeModel.getUser_role_availability());
		permissionQuery.setParameter("login", roleTypeModel.isLogin());
		permissionQuery.setParameter("n", request.getName());
		permissionQuery.executeUpdate();
		
		tx.commit();
		session.close();
		
		RoleTypeResponseModel responseModel = new RoleTypeResponseModel();
		ResultModel result = new ResultModel();
		result.setStatus("Ok");
		result.setMessage("Update!");
		responseModel.setResult(result);
//		responseModel.setRole(request);
		
		return responseModel;
	}
	
	public RolesListResponseModel rolesList(){
		
		Configuration con = new Configuration().configure().addAnnotatedClass(RoleTypeModel.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query permissionQuery = session.createQuery("from RoleTypeModel");
		List<RoleTypeModel> roleTypeList =  permissionQuery.getResultList();
		
		for(int i=0; i<roleTypeList.size(); i++){
			roleTypeList.get(i).setName(roleTypeList.get(i).getName().toUpperCase());
		}
		
		tx.commit();
		session.close();
		
		RolesListResponseModel responseModel = new RolesListResponseModel();
		responseModel.setRolesList(roleTypeList);
		ResultModel resultModel = new ResultModel();
		resultModel.setStatus("OK");
		resultModel.setMessage("Listed Roles!");
		responseModel.setResult(resultModel);
		
		return responseModel;
	}

}
