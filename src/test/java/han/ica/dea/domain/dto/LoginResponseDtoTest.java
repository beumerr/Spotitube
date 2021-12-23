package han.ica.dea.domain.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginResponseDtoTest {

    private LoginResponseDto sut;
    
    private String user = "user";
    private String newUser = "newUser";
    private String token = "token";
    private String newToken = "newToken";
    
    @BeforeEach
    public void setup() {
    	sut = new LoginResponseDto(user, token);
    }
    
    @Nested
    @DisplayName("`LoginResponseDto.user` Tests")
    class LoginResponseDtoUserTests {
    	
    	@Test
        public void testGetUser() {
    		var expected = user;
        	var actual = sut.getUser();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetUser() {
            var expected = newUser;
            sut.setUser(newUser);
            var actual = sut.getUser();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`LoginResponseDto.token` Tests")
    class LoginResponseDtoTokenTests {
    	
    	@Test
        public void testGetToken() {
    		var expected = token;
        	var actual = sut.getToken();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetToken() {
            var expected = newToken;
            sut.setToken(newToken);
            var actual = sut.getToken();

            assertEquals(expected, actual);
        }
    }
}



















