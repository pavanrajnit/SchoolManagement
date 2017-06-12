package org.school.tool.management.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.tool.management.model.ResultModel;
import org.school.tool.management.model.RoleTypeModel;
import org.school.tool.management.model.RoleTypeRequestModel;
import org.school.tool.management.model.RoleTypeResponseModel;
import org.school.tool.management.model.RolesListResponseModel;
import org.school.tool.management.services.RoleTypeServices;
import org.school.tool.management.services.SessionCheckServices;

@Path("role")
public class RoleTypeApi {
	
	RoleTypeServices services = new RoleTypeServices();
	SessionCheckServices sessionService = new SessionCheckServices();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RolesListResponseModel roleTypeList(@HeaderParam("token") String token){
		if(sessionService.checkSession(token)){
			return services.rolesList();	
		}else {
			RolesListResponseModel responseModel = new RolesListResponseModel();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Error");
			resultModel.setMessage("Token Expired!");
			responseModel.setResult(resultModel);
			return responseModel;
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoleTypeResponseModel createRoleType(@HeaderParam("token") String token, RoleTypeRequestModel requestModel){
		if(sessionService.checkSession(token)){
			return services.createRole(requestModel);	
		}else {
			RoleTypeResponseModel responseModel = new RoleTypeResponseModel();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Error");
			resultModel.setMessage("Token Expired!");
			responseModel.setResult(resultModel);
			return responseModel;
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoleTypeResponseModel updateRoleType(@HeaderParam("token") String token, RoleTypeRequestModel requestModel){
		if(sessionService.checkSession(token)) {
			return services.updateRole(requestModel);
		}else {
			RoleTypeResponseModel responseModel = new RoleTypeResponseModel();
			ResultModel resultModel = new ResultModel();
			resultModel.setStatus("Error");
			resultModel.setMessage("Token Expired!");
			responseModel.setResult(resultModel);
			return responseModel;
		}
		
	}
	
	

}
