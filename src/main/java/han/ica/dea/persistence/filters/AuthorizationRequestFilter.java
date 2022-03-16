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

import org.ehcache.Cache;

import han.ica.dea.domain.dto.LoginResponseDto;
import han.ica.dea.persistence.cache.CacheHelper;
import han.ica.dea.persistence.dao.LoginDao;
 
@PreMatching
@Provider
public class AuthorizationRequestFilter implements ContainerRequestFilter {
 
	private LoginDao loginDao;
    	
	private Cache<String, LoginResponseDto> sessionCache;
	
	private ContainerRequestContext rc;
	
	private UriInfo uriInfo;
	
	private int userId = 0;
	
	private boolean isRequestSet = false;
	
    @Inject
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
	
    @Override
    public void filter(ContainerRequestContext rc) throws IOException {
    	this.rc = rc;
    	this.uriInfo = rc.getUriInfo();
    	
    	if(!uriInfo.getPath().equals("login")) {
    		
    		setCache();
    		MultivaluedMap<String, String> params = uriInfo.getQueryParameters();
    		params.forEach((key, value) -> {
    			
    			if(key.equals("token") && !isRequestSet) {
    				setTokenUserId(value.get(0));
    				if(validateToken(value.get(0))) {
    					setRequestUri();
    				} else {
    					abort("Token is invalid.");
    				}
    			}
    		}); 
    	}
    }
    
    private boolean validateToken(String token) {
    	return validateExperity(token) && validateValue();
    }
    
    private void setCache() {
    	sessionCache = CacheHelper.get().create(CacheHelper.CacheAlias.SESSION);
    }
    
    private boolean validateExperity(String token) {
    	return sessionCache.get(token) != null;
    }
    
    private boolean validateValue() {
    	return userId != 0;	
    }
    
    private void setTokenUserId(String token) {
    	this.userId = loginDao.getUserId(token);
    }
    
    private void setRequestUri() {
    	if(userId == 0) {
    		abort("UserId is not set");
    		return;
    	}
    	
    	UriBuilder builder = uriInfo
			.getRequestUriBuilder()
			.queryParam("userId", userId)
			.replaceQueryParam("token");
				
		rc.setRequestUri(uriInfo.getBaseUri(), builder.build());
		isRequestSet = true;
    }
    
    private void abort(String msg) {
    	rc.abortWith(Response
            .status(Response.Status.UNAUTHORIZED)
            .entity(msg)
            .build()
        );
    }

}
