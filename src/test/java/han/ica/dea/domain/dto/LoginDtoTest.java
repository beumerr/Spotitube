package han.ica.dea.domain.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDtoTest {

    private LoginDto sut;
    
    private String user = "user";
    private String newUser = "newUser";
    private String password = "password";
    private String newPassword = "newPassword";
    
    @BeforeEach
    public void setup() {
    	sut = new LoginDto(user, password);
    }
    
    @Nested
    @DisplayName("`LoginDto.user` Tests")
    class LoginDtoUserTests {
    	
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
    @DisplayName("`LoginDto.password` Tests")
    class LoginDtoPasswordTests {
    	
    	@Test
        public void testGetPassword() {
    		var expected = password;
        	var actual = sut.getPassword();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetPassword() {
            var expected = newPassword;
            sut.setPassword(newPassword);
            var actual = sut.getPassword();

            assertEquals(expected, actual);
        }
    }
}



















