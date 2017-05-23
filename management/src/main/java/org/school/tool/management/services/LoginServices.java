package org.school.tool.management.services;

import org.hibernate.query.Query;

import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.school.tool.management.model.LoginModel;
import org.school.tool.management.model.LoginRequestModel;
import org.school.tool.management.model.LoginResponseModel;
import org.school.tool.management.model.LoginResposeResultModel;
import org.school.tool.management.model.PermissionTableModel;
import org.school.tool.management.model.RoleTypeModel;
import org.school.tool.management.model.SessionModel;

public class LoginServices {
	
	public LoginResponseModel validateLogin(LoginRequestModel userModel){
		
		String userName = userModel.getUserName();
		String password = userModel.getPassword();
		
		Configuration con = new Configuration().configure().addAnnotatedClass(LoginModel.class).
									addAnnotatedClass(SessionModel.class).addAnnotatedClass(RoleTypeModel.class);
		
//		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		
		SessionFactory sf = con.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query loginQuery = session.createQuery("from LoginModel where userName = :userName and password = :password");
		loginQuery.setParameter("userName", userName);
		loginQuery.setParameter("password", password);
		LoginModel model = (LoginModel) loginQuery.uniqueResult();
		
		tx.commit();
		
		if (model == null){
			return null;
		}else {
			Session conSession = sf.openSession();
			Transaction conTx = conSession.beginTransaction();
			SessionModel sessionModel = new SessionModel();
			sessionModel.setId(0);
			sessionModel.setSessionToken(UUID.randomUUID().toString().replaceAll("-", ""));
			sessionModel.setLoginId(model.getId());
			conSession.save(sessionModel);
			conTx.commit();
			
			Session permissionSession = sf.openSession();
			Transaction permissionTx = permissionSession.beginTransaction();
			Query permissionQuery = permissionSession.createQuery("from RoleTypeModel where name = :name");
			permissionQuery.setParameter("name", model.getRoleType());
			RoleTypeModel permissionTableModel = (RoleTypeModel) permissionQuery.uniqueResult();
			permissionTx.commit();
			
			System.out.println(model.getId() + " " + model.getUserName() + " " + model.getPassword() + " " + model.getRoleType());
			LoginResponseModel responseModel = new LoginResponseModel();
			LoginResposeResultModel resultModel = new LoginResposeResultModel();
			resultModel.setStatus("Ok");
			resultModel.setMessage("Login Success!");
			responseModel.setResult(resultModel);
			responseModel.setRoleType(model.getRoleType());
			responseModel.setSessiontoken(sessionModel.getSessionToken());
			responseModel.setUserRoleAvailability(permissionTableModel.getUserRoleAvailability());
			
//			StringBuilder permissionBuilder = new StringBuilder();
//			if (permissionTableModel.isCanAccessAcademicReport()) permissionBuilder.append("ACADEMIC_REPORT, ");
//			if (permissionTableModel.isCanAccessAttendanceReport()) permissionBuilder.append("ATTENDANCE_REPORT, ");
//			if (permissionTableModel.isCanAccessHistory()) permissionBuilder.append("CHANGE_HISTORY, ");
//			if (permissionTableModel.isCanAccessOrganizationalSettings()) permissionBuilder.append("ORGANIZATIONAL_SETTINGS, ");
//			if (permissionTableModel.isCanAccessUserSettings()) permissionBuilder.append("USER_SETTINGS, ");
//			if (permissionTableModel.isCanEditHolidayList()) permissionBuilder.append("EDIT_HOLIDAY_LIST, ");
//			if (permissionTableModel.isCanViewHolidayList()) permissionBuilder.append("VIEW_HOLIDAY_LIST, ");
//			if (permissionTableModel.isCanEditStudent()) permissionBuilder.append("EDIT_STUDENT, ");
//			if (permissionTableModel.isCanViewStudent()) permissionBuilder.append("VIEW_STUDENT, ");
//			if (permissionTableModel.isCanEditTeacher()) permissionBuilder.append("EDIT_TEACHER, ");
//			if (permissionTableModel.isCanViewTeacher()) permissionBuilder.append("VIEW_TEACHER, ");
//			if (permissionTableModel.isCanEditTimeTable()) permissionBuilder.append("EDIT_TIME_TABLE, ");
//			if (permissionTableModel.isCanViewTimeTable()) permissionBuilder.append("VIEW_TIME_TABLE, ");
//			if (permissionTableModel.isCanPayFees()) permissionBuilder.append("PAY_FEE, ");
//			if (permissionTableModel.isCanRegister()) permissionBuilder.append("REGISTRATION, ");
//			if (permissionTableModel.isCanEditNonTeachingStaff()) permissionBuilder.append("EDIT_NON_TEACHING_STAFF, ");
//			if (permissionTableModel.isCanViewNonTeachingStaff()) permissionBuilder.append("VIEW_NON_TEACHING_STAFF, ");
			
			String permission = permissionTableModel.getPermissions();
			String[] permissions = permission.split(", ");
			
			responseModel.setPermissions(permissions);
			return responseModel;
		}
		
	}

}
