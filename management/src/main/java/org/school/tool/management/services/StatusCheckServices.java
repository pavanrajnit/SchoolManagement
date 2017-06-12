package org.school.tool.management.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.school.tool.management.model.LoginModel;
import org.school.tool.management.model.LoginResponseModel;
import org.school.tool.management.model.LoginResponseUserModel;
import org.school.tool.management.model.ResultModel;
import org.school.tool.management.model.RoleTypeModel;
import org.school.tool.management.model.StatusCheckRequestModel;
import org.school.tool.management.model.StatusCheckResponseModel;

public class StatusCheckServices {
	
	public StatusCheckResponseModel checkStatus(StatusCheckRequestModel request, String token){
		
		Configuration con = new Configuration().configure().addAnnotatedClass(LoginModel.class).addAnnotatedClass(RoleTypeModel.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Query loginQuery = session.createQuery("from LoginModel where email = :email");
		loginQuery.setParameter("email", request.getEmail());
		LoginModel model = (LoginModel) loginQuery.uniqueResult();
		
		if(model != null && model.isLogged_in()){
			Session permissionSession = sf.openSession();
			Transaction permissionTx = permissionSession.beginTransaction();
			Query permissionQuery = permissionSession.createQuery("from RoleTypeModel where name = :name");
			permissionQuery.setParameter("name", model.getRoleType());
			RoleTypeModel permissionTableModel = (RoleTypeModel) permissionQuery.uniqueResult();
			permissionTx.commit();
			permissionSession.close();
			
			StatusCheckResponseModel responseModel = new StatusCheckResponseModel();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Ok");
			resultModel.setMessage("User Logged In!");
			responseModel.setResult(resultModel);
			
			responseModel.setSession_token(token);
			
			LoginResponseUserModel responseUserModel = new LoginResponseUserModel();
			responseUserModel.setUser_id(model.getUserId());
			responseUserModel.setFirstname(model.getFirstName());
			responseUserModel.setLastname(model.getLastName());
			responseUserModel.setUser_role(model.getRoleType());
			responseUserModel.setUser_role_availability(permissionTableModel.getUser_role_availability());
			String permission = permissionTableModel.getPermissions();
			String[] permissions = permission.split(", ");
			responseUserModel.setPermissions(permissions);
			
			responseModel.setUser(responseUserModel);
			return responseModel;
		}else {
			StatusCheckResponseModel responseModel = new StatusCheckResponseModel();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Error");
			resultModel.setMessage("User Logged Out");
			responseModel.setResult(resultModel);
			return responseModel;
		}
	
	}

}
