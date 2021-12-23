package han.ica.dea.persistence.filters;

import java.io.IOException;

import javax.inject.Inject;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import han.ica.dea.persistence.dao.LoginDao;
 
@PreMatching
@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {
 
	private LoginDao loginDao;
	
    @Override
    public void filter(ContainerRequestContext rc) throws IOException {
    	final UriInfo uriInfo = rc.getUriInfo();
    	
    	if(!uriInfo.getPath().equals("login")) {
    		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
    		params.forEach((key, value) -> {
    			if(key.equals("token")) {
    				validateToken(rc, uriInfo, value.get(0));
    			}
    		}); 
    	}
    }
    
    private void validateToken(
    		ContainerRequestContext rc, 
    		UriInfo uriInfo, 
    		String token
    		) {
    	final int userId = loginDao.getUserId(token);
  
		if(userId != 0) 	{
			UriBuilder builder = uriInfo
				.getRequestUriBuilder()
				.queryParam("userId", userId)
				.replaceQueryParam("token");
				
			rc.setRequestUri(uriInfo.getBaseUri(), builder.build());
		}
		else {
			rc.abortWith(Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Token is missing or invalid.")
                .build()
            );
		}
    }
    
    @Inject
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
    
    public LoginDao getLoginDao() {
    	return loginDao;
    }  
}
