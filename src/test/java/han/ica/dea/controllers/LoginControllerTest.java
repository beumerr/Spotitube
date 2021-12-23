package han.ica.dea.controllers;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import han.ica.dea.persistence.dao.LoginDao;
import han.ica.dea.domain.dto.LoginDto;
import han.ica.dea.domain.exceptions.AuthenticateException;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.ServerErrorException;
import han.ica.dea.services.LoginService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

	private LoginController sut;

	private LoginService loginServiceMock;

	private LoginDto loginDto;

	private LoginDao loginDaoMock;

	private String username = "meron";
	private String password = "MySuperSecretPassword12341";

	@BeforeEach
	public void setup() {
		sut = new LoginController();
		loginDto = new LoginDto(username, password);
		loginServiceMock = Mockito.mock(LoginService.class);
		loginDaoMock = Mockito.mock(LoginDao.class);
		loginServiceMock.setLoginDao(loginDaoMock);
		sut.setLoginService(loginServiceMock);
	}
	
	@Nested
    @DisplayName("Injection tests")
    class LoginInjection {
		
		@Test
		public void testLoginServiceInjection() {
			var expected = loginServiceMock;
			var result = sut.getLoginService();

			assertEquals(expected, result);
		}

		@Test
		public void testSetLoginService() {
			var expected = loginServiceMock;
			sut.setLoginService(loginServiceMock);
			var result = sut.getLoginService();

			assertEquals(expected, result);
		}
	}
	
	@Nested
    @DisplayName("`login` tests")
    class HandleLoginTest {
		
		@Test
		public void testLoginResponseOk() {
			var expected = Response.Status.OK;
			var result = sut.login(loginDto);

			assertEquals(expected.getStatusCode(), result.getStatus());
		}

		@Test
		public void testLoginAuthenticateException() {
			var expected = new AuthenticateException();
			when(loginServiceMock.validateUser(loginDto)).thenThrow(expected);

			assertThrows(AuthenticateException.class, () -> sut.login(loginDto));
		}

		@Test
		public void testLoginBadRequestException() {
			var expected = new BadRequestException();
			when(loginServiceMock.validateUser(loginDto)).thenThrow(expected);

			assertThrows(BadRequestException.class, () -> sut.login(loginDto));
		}

		@Test
		public void testLoginServerErrorException() {
			var expected = new ServerErrorException();
			when(loginServiceMock.validateUser(loginDto)).thenThrow(expected);

			assertThrows(ServerErrorException.class, () -> sut.login(loginDto));
		}

		@Test
		public void testLoginCallsValidateUser() {
			sut.login(loginDto);

			verify(loginServiceMock, times(1)).validateUser(loginDto);
		}
	}
}
