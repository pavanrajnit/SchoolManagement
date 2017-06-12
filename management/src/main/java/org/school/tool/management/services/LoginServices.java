package org.school.tool.management.services;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.school.tool.management.model.LoginModel;
import org.school.tool.management.model.LoginRequestModel;
import org.school.tool.management.model.LoginResponseModel;
import org.school.tool.management.model.LoginResponseUserModel;
import org.school.tool.management.model.ResultModel;
import org.school.tool.management.model.RoleTypeModel;
import org.school.tool.management.model.SessionModel;

public class LoginServices {
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public LoginResponseModel validateLogin(LoginRequestModel userModel){
		
		String email = userModel.getEmail();
		String password = userModel.getPassword();
		
		if(!validate(email)){
			LoginResponseModel responseModel = new LoginResponseModel();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Error");
			resultModel.setMessage("Email invalid!");
			responseModel.setResult(resultModel);
			return responseModel;
		}
		
		Configuration con = new Configuration().configure().addAnnotatedClass(LoginModel.class).
									addAnnotatedClass(SessionModel.class).addAnnotatedClass(RoleTypeModel.class);
		
//		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		
		SessionFactory sf = con.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query loginQuery = session.createQuery("from LoginModel where email = :email and password = :password");
		loginQuery.setParameter("email", email);
		loginQuery.setParameter("password", password);
		LoginModel model = (LoginModel) loginQuery.uniqueResult();
		
		tx.commit();
		session.close();
		
		if (model == null){
			LoginResponseModel responseModel = new LoginResponseModel();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Error");
			resultModel.setMessage("Email or Password is incorrect!");
			responseModel.setResult(resultModel);
			return responseModel;
		}else {	
			Session conSession = sf.openSession();
			Transaction conTx = conSession.beginTransaction();
			SessionModel sessionModel = new SessionModel();
			sessionModel.setSessionId(0);
			sessionModel.setSessionToken(UUID.randomUUID().toString().replaceAll("-", ""));
			sessionModel.setLoginId(model.getId());
			if (userModel.isRemember()){
				TimeZone indianTimeZone = TimeZone.getTimeZone("Asia/Calcutta");
				Calendar calendar = Calendar.getInstance(indianTimeZone);
				long time = calendar.getTimeInMillis();
				Date sessionTime = new Date(time + (10*24*60*60000));
				sessionModel.setSession_timeout(sessionTime);
			}else {
				TimeZone indianTimeZone = TimeZone.getTimeZone("Asia/Calcutta");
				Calendar calendar = Calendar.getInstance(indianTimeZone);
				long time = calendar.getTimeInMillis();
				Date sessionTime = new Date(time + (5*60000));
				sessionModel.setSession_timeout(sessionTime);
			}
			conSession.save(sessionModel);
			conTx.commit();
			conSession.close();
			
			Session loginSession = sf.openSession();
			Transaction loginTx = loginSession.beginTransaction();
			Query query = loginSession.createQuery("update LoginModel set remember=:remember, logged_in=:logged where loginId =:loginId");
			query.setParameter("remember", userModel.isRemember());
			query.setParameter("logged", true);
			query.setParameter("loginId", model.getLoginId());
			query.executeUpdate();
			loginTx.commit();
			loginSession.close();
			
			Session permissionSession = sf.openSession();
			Transaction permissionTx = permissionSession.beginTransaction();
			Query permissionQuery = permissionSession.createQuery("from RoleTypeModel where name = :name");
			permissionQuery.setParameter("name", model.getRoleType());
			RoleTypeModel permissionTableModel = (RoleTypeModel) permissionQuery.uniqueResult();
			permissionTx.commit();
			permissionSession.close();
			
			LoginResponseModel responseModel = new LoginResponseModel();
			responseModel.setSession_token(sessionModel.getSessionToken());
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Ok");
			resultModel.setMessage("Login Success!");
			responseModel.setResult(resultModel);
			LoginResponseUserModel responseUserModel = new LoginResponseUserModel();
			responseUserModel.setUser_id(model.getUserId());
			responseUserModel.setFirstname(model.getFirstName());
			responseUserModel.setLastname(model.getLastName());
			responseUserModel.setUser_role(model.getRoleType());
			responseUserModel.setUser_role_availability(permissionTableModel.getUser_role_availability());
			responseUserModel.setEmail(model.getEmail());
			
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
			responseUserModel.setPermissions(permissions);
			responseModel.setUser(responseUserModel);
			
			return responseModel;
		}
		
	}
	
	public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
}

}
