package han.ica.dea.controllers;

import han.ica.dea.domain.dto.LoginDto;
import han.ica.dea.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginController {

    private LoginService loginService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginDto loginDto) {
    	return Response
                .status(Response.Status.OK)
                .entity(loginService.validateUser(loginDto))
                .build();
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public LoginService getLoginService() {
        return loginService;
    }
    
}
