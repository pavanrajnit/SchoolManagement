package org.school.tool.management.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.school.tool.management.model.LoginRequestModel;
import org.school.tool.management.model.LoginResponseModel;
import org.school.tool.management.services.LoginServices;

@Path("login")
public class LoginApi {
	
	private LoginServices services = new LoginServices();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginResponseModel verifySuperUser(LoginRequestModel userModel){
		return services.validateLogin(userModel);
	}

}
