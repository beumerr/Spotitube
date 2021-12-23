package han.ica.dea.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import han.ica.dea.persistence.dao.LoginDao;
import han.ica.dea.domain.dto.LoginDto;
import han.ica.dea.domain.dto.LoginResponseDto;
import han.ica.dea.domain.exceptions.AuthenticateException;
import han.ica.dea.domain.exceptions.MissingTokenException;
import han.ica.dea.domain.exceptions.ServerErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    private LoginService sut;
    
    private LoginDto loginDto;
    
    private LoginResponseDto loginResponseDto;
    
    private LoginDao loginDaoMock;
    
    private static String username = "meron";
    private static String password = "MySuperSecretPassword12341";
    private static String tokenOld = "1234-1234-1234";
    private static String tokenNew = "4321-4321-4321";

    @BeforeEach
    public void setup() {
        sut = new LoginService();
        loginDaoMock = Mockito.mock(LoginDao.class);
        sut.setLoginDao(loginDaoMock);
        loginDto = new LoginDto(username, password);
        loginResponseDto = Mockito.mock(LoginResponseDto.class);
    }
    
    @Nested
    @DisplayName("`validateUser` Tests")
    class ValidateUserTests {
    	
    	@Test
        public void testValidateUserCalls() {
        	when(loginDaoMock.getUser(loginDto)).thenReturn(loginResponseDto);
        	when(loginDaoMock.generateToken(loginDto)).thenReturn(tokenNew);
        	sut.validateUser(loginDto);
        	
        	verify(loginDaoMock, times(1)).getUser(loginDto);
            verify(loginDaoMock, times(1)).generateToken(loginDto);
            verify(loginResponseDto, times(1)).setToken(tokenNew);
        }

        @Test
        public void testValidateUserSetToken() {
        	var expected = new LoginResponseDto(username, tokenOld);
            when(loginDaoMock.getUser(loginDto)).thenReturn(loginResponseDto);
            when(loginDaoMock.generateToken(loginDto)).thenReturn(tokenNew);
            var result = sut.validateUser(loginDto);

            assertNotEquals(expected.getToken(), result.getToken());
        }
        
        @Test
        public void testValidateUserSuccesResponsee() {
        	var expected = loginResponseDto;
            when(loginDaoMock.getUser(loginDto)).thenReturn(loginResponseDto);
            var result = sut.validateUser(loginDto);
            
            assertEquals(expected, result);
        }
        
        @Test
        public void testValidateUserMissingTokenException() {
        	when(loginDaoMock.getUser(loginDto)).thenReturn(null);

            assertThrows(MissingTokenException.class, () -> sut.validateUser(loginDto));
        }
        
        @Test
        public void testValidateUserGetUserSQLException() {
        	var expected = new AuthenticateException();
        	when(loginDaoMock.getUser(loginDto)).thenThrow(expected);
        	
        	assertThrows(AuthenticateException.class, () -> sut.validateUser(loginDto));
        }
        
        
        @Test
        public void testValidateUserGetUserException() {
        	var expected = new ServerErrorException();
        	when(loginDaoMock.getUser(loginDto)).thenThrow(expected);
        	
        	assertThrows(ServerErrorException.class, () -> sut.validateUser(loginDto));
        }
    }
}



















