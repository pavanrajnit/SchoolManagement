package org.school.tool.management.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.school.tool.management.model.RoleTypeRequestModel;
import org.school.tool.management.model.RoleTypeResponseModel;
import org.school.tool.management.services.RoleTypeServices;

@Path("role")
public class RoleTypeApi {
	
	RoleTypeServices services = new RoleTypeServices();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoleTypeResponseModel createRoleType(RoleTypeRequestModel requestModel){
		return services.createRole(requestModel);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RoleTypeResponseModel updateRoleType(RoleTypeRequestModel requestModel){
		return services.updateRole(requestModel);
	}
	
	

}
