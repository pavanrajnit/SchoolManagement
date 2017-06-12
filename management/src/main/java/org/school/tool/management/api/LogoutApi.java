package org.school.tool.management.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.tool.management.model.LogoutRequestModel;
import org.school.tool.management.model.LogoutResponseModel;
import org.school.tool.management.model.ResultModel;
import org.school.tool.management.services.LogoutServices;
import org.school.tool.management.services.SessionCheckServices;

@Path("logout")
public class LogoutApi {
	
	LogoutServices service = new LogoutServices();
	SessionCheckServices sessionService = new SessionCheckServices();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LogoutResponseModel userLogout(@HeaderParam("token") String token, LogoutRequestModel request){
		
		if(sessionService.checkSession(token)){
			return service.userLogout(request);	
		}else {
			LogoutResponseModel responseModel = new LogoutResponseModel();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Error");
			resultModel.setMessage("Token Expired!");
			responseModel.setResult(resultModel);
			return responseModel;
		}
		
	}

}
