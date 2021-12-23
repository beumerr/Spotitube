package han.ica.dea.persistence.mappings.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import han.ica.dea.domain.dto.LoginResponseDto;

public class LoginResponseDataMapperTest  {
	
	private LoginResponseDataMapper sut;
	private ResultSet resultSetMock;
	
	private static final String fullname = "fullname";
	private static final String token = "token";
	
	@BeforeEach
	public void setup() {
	    sut = new LoginResponseDataMapper();
	    resultSetMock = mock(ResultSet.class);
	}
	
	@Nested
	public class LoginResponseMapperTests {
		
	    @Test
	    public void testReturnWaardeMapToDTO() {
	        try {
	            var expected = new LoginResponseDto(fullname, token);
	            when(resultSetMock.getString("fullname")).thenReturn(fullname);
	            when(resultSetMock.getString("token")).thenReturn(token);
	            var actual = sut.mapToDTO(resultSetMock);
	
	            assertEquals(expected.getUser(), actual.getUser());
	            assertEquals(expected.getToken(), actual.getToken());
	        } catch(SQLException e) {
	            fail();
	        }
	    }
	  
	}
}
