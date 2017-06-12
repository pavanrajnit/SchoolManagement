package org.school.tool.management.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.tool.management.model.UserModel;
import org.school.tool.management.model.UserModelObject;
import org.school.tool.management.model.UserRequestModel;
import org.school.tool.management.model.UserResponseModel;
import org.school.tool.management.services.SessionCheckServices;
import org.school.tool.management.services.UserServices;

@Path("users")
public class UserApi {
	
	UserServices us = new UserServices();
	SessionCheckServices sessionService = new SessionCheckServices();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> getUsers(@HeaderParam("token")String token){
		if(sessionService.checkSession(token)){
			return us.getUsers();	
		}else {
			return null;
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponseModel addUser(@HeaderParam("token") String token, UserRequestModel user){
		if(sessionService.checkSession(token)){
			return us.addUser(user);	
		}else {
			return null;
		}
	}
}
