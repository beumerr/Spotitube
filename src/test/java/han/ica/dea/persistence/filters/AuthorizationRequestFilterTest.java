package han.ica.dea.persistence.filters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import han.ica.dea.persistence.dao.LoginDao;
 
public class AuthorizationRequestFilterTest  {
	
	private AuthorizationRequestFilter sut;
	
	private LoginDao loginDaoMock;
	
	private ContainerRequestContext crxonMock;
	
	private UriInfo uriInfoMock;
	
	private UriBuilder uriBuilderMock;
	
	private MultivaluedMap<String, String> uriInfo = new MultivaluedHashMap<String, String>();

	private static final String TOKEN = "token";
	
	@BeforeEach
	public void setup() {
		sut = new AuthorizationRequestFilter();
		loginDaoMock = mock(LoginDao.class);
		crxonMock = mock(ContainerRequestContext.class);
		uriInfoMock = mock(UriInfo.class);
		uriBuilderMock = mock(UriBuilder.class);
		sut.setLoginDao(loginDaoMock);
		uriInfo.add("token", TOKEN);
	}
	
	
	@Nested
	@DisplayName("`filter` tests")
	class FilterTests {
		
		@Test
	    public void testValidateTokenCall() {
	    	try {
	    		when(crxonMock.getUriInfo()).thenReturn(uriInfoMock);
	    		when(uriInfoMock.getPath()).thenReturn("notLogin");
	    		when(uriInfoMock.getQueryParameters()).thenReturn(uriInfo);
	    		
				sut.filter(crxonMock);
				
				verify(loginDaoMock, times(1)).getUserId(TOKEN);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		
		@Test
	    public void testIsLogin() {
	    	try {
	    		when(crxonMock.getUriInfo()).thenReturn(uriInfoMock);
	    		when(uriInfoMock.getPath()).thenReturn("login");
	    		
				sut.filter(crxonMock);
				
				verify(loginDaoMock, times(0)).getUserId(TOKEN);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		
		@Test
	    public void testTokenNotSet() {
	    	try {
	    		var unkownParams = new MultivaluedHashMap<String, String>();
	    		unkownParams.add("unkownKey", TOKEN);
	    		when(crxonMock.getUriInfo()).thenReturn(uriInfoMock);
	    		when(uriInfoMock.getPath()).thenReturn("notLogin");
	    		when(uriInfoMock.getQueryParameters()).thenReturn(unkownParams);

				sut.filter(crxonMock);
				
				verify(loginDaoMock, times(0)).getUserId(TOKEN);
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	@Nested
	@DisplayName("`validateToken` tests")
	class ValidateTokenTests {
		
		@Test
	    public void testValidateTokenSetRequestUri() {
	    	try {
	    		when(crxonMock.getUriInfo()).thenReturn(uriInfoMock);
	    		when(uriInfoMock.getPath()).thenReturn("notLogin");
	    		when(uriInfoMock.getQueryParameters()).thenReturn(uriInfo);
	    		when(loginDaoMock.getUserId(TOKEN)).thenReturn(1);
	    		when(uriInfoMock.getRequestUriBuilder()).thenReturn(uriBuilderMock);
	    		when(uriBuilderMock.queryParam("userId", 1)).thenReturn(uriBuilderMock);
	    		when(uriBuilderMock.replaceQueryParam("token")).thenReturn(uriBuilderMock);
	    		
				sut.filter(crxonMock);
				
				verify(crxonMock, times(1)).setRequestUri(uriInfoMock.getBaseUri(), uriBuilderMock.build());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
		
		@Test
	    public void testValidateTokenAbort() {
	    	try {
	    		when(crxonMock.getUriInfo()).thenReturn(uriInfoMock);
	    		when(uriInfoMock.getPath()).thenReturn("notLogin");
	    		when(uriInfoMock.getQueryParameters()).thenReturn(uriInfo);
	    		when(loginDaoMock.getUserId(TOKEN)).thenReturn(0);
	    	
				sut.filter(crxonMock);
				
				verify(crxonMock, times(1)).abortWith(any(Response.class));
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
}
