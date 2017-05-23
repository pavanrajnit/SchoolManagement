package org.school.tool.management.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.tool.management.model.UserModel;
import org.school.tool.management.model.UserModelObject;
import org.school.tool.management.model.UserRequestModel;
import org.school.tool.management.model.UserResponseModel;
import org.school.tool.management.services.UserServices;

@Path("users")
public class UserApi {
	
	UserServices us = new UserServices();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserModel> getUsers(){
		return us.getUsers();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserResponseModel addUser(UserRequestModel user){
		return us.addUser(user);
	}
}
