package org.school.tool.management.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.school.tool.management.model.LoginModel;
import org.school.tool.management.model.UserModel;
import org.school.tool.management.model.UserModelObject;
import org.school.tool.management.model.UserRequestModel;
import org.school.tool.management.model.UserResponseModel;

public class UserServices {

	public UserServices(){
		
	}
	
	public List<UserModel> getUsers(){
		
		Configuration con = new Configuration().configure().addAnnotatedClass(UserModel.class);
		
		SessionFactory sf = con.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from UserModel");
		List<UserModel> userModels = query.getResultList();
		
		tx.commit();
		
		return userModels;
	}
	
	public UserResponseModel addUser(UserRequestModel user){
		UserModel userModel = new UserModel();
		userModel.setUserId(0);
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		userModel.setGender(user.getGender());
		userModel.setDepartments(user.getDepartments());
		userModel.setFathersName(user.getFathersName());
		userModel.setEmail(user.getEmail());
		userModel.setMobile(user.getMobile());
		userModel.setAddress(user.getAddress());
		userModel.setDateOfJoining(user.getDateOfJoining());
		userModel.setSalary(user.getSalary());
		userModel.setLoginAvailable(user.isLoginAvailable());
		
		Configuration con = new Configuration().configure().addAnnotatedClass(UserModel.class);
		
		SessionFactory sf = con.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		session.save(userModel);
		
		tx.commit();
		
		UserResponseModel response = new UserResponseModel();
		response.setStatus("Added Successfully!");
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setGender(user.getGender());
		response.setDepartments(user.getDepartments());
		response.setFathersName(user.getFathersName());
		response.setEmail(user.getEmail());
		response.setMobile(user.getMobile());
		response.setAddress(user.getAddress());
		response.setDateOfJoining(user.getDateOfJoining());
		response.setSalary(user.getSalary());
		response.setLoginAvailable(user.isLoginAvailable());
		
		return response;
	}
}
