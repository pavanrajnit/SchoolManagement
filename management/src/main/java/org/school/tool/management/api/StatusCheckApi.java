package org.school.tool.management.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.tool.management.model.ResultModel;
import org.school.tool.management.model.RoleTypeResponseModel;
import org.school.tool.management.model.StatusCheckRequestModel;
import org.school.tool.management.model.StatusCheckResponseModel;
import org.school.tool.management.services.SessionCheckServices;
import org.school.tool.management.services.StatusCheckServices;

@Path("status")
public class StatusCheckApi {
	
	StatusCheckServices service = new StatusCheckServices();
	SessionCheckServices sessionService = new SessionCheckServices();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public StatusCheckResponseModel checkStatus(@HeaderParam("token") String token, StatusCheckRequestModel requestModel){
		
		if(sessionService.checkSession(token)){
			return service.checkStatus(requestModel, token);	
		}else {
			StatusCheckResponseModel responseModel = new StatusCheckResponseModel();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Error");
			resultModel.setMessage("Token Expired!");
			responseModel.setResult(resultModel);
			return responseModel;
		}
		
	}
	
}
