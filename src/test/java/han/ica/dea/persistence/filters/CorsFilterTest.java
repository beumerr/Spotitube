package han.ica.dea.persistence.filters;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;

import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.ext.Provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@Provider
public class CorsFilterTest {

	private CorsFilter sut;
	
	private ContainerResponseContext cresMock;
	
	private ContainerRequestContext requestContextMock;
	
	@BeforeEach
	public void setup() {
		sut = new CorsFilter();
		cresMock = mock(ContainerResponseContext.class);
		requestContextMock = mock(ContainerRequestContext.class);
	}
	
	
	@Test
	@SuppressWarnings("unchecked")
    public void testValidateTokenCall() {
    	try {
    		var mock = spy(MultivaluedMap.class);
    		when(cresMock.getHeaders()).thenReturn(mock);
			sut.filter(requestContextMock, cresMock);

			verify(mock, times(1)).add("Access-Control-Allow-Origin", "*");
			verify(mock, times(1)).add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
			verify(mock, times(1)).add("Access-Control-Allow-Credentials", "true");
			verify(mock, times(1)).add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
			verify(mock, times(1)).add("Access-Control-Allow-Origin", "*");
			verify(mock, times(1)).add("Access-Control-Max-Age", "1209600");
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
    }

}