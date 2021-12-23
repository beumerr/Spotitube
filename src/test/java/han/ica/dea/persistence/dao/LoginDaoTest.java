package han.ica.dea.persistence.dao;

import han.ica.dea.domain.dto.LoginDto;
import han.ica.dea.domain.dto.LoginResponseDto;
import han.ica.dea.domain.exceptions.AuthenticateException;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.ServerErrorException;
import han.ica.dea.persistence.context.DbConnection;
import han.ica.dea.persistence.mappings.data.LoginResponseDataMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.sql.*;

public class LoginDaoTest {
	
	private LoginDao sut;
	
    private LoginResponseDataMapper loginResponseMapperMock;
    
    private DbConnection dbConnectionMock;
    
    private Connection connectionMock;
    
    private PreparedStatement preparedStatementMock;
    
    private ResultSet resultSetMock;
    
    private LoginDto loginDto = new LoginDto(USER, PASSWORD);
    
    private LoginResponseDto loginResponseDto = new LoginResponseDto(USER, TOKEN);

    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String TOKEN = "token";

    @BeforeEach
    public void setup() {
        sut = new LoginDao();
        loginResponseMapperMock = mock(LoginResponseDataMapper.class);
        dbConnectionMock = mock(DbConnection.class);
        resultSetMock = mock(ResultSet.class);
        connectionMock = mock(Connection.class);
        preparedStatementMock = mock(PreparedStatement.class);
        sut.setMapper(loginResponseMapperMock);
        sut.setConnection(dbConnectionMock);
    }
    
    @Nested
    @DisplayName("Injection tests")
    public class InjectionTests {
    	
    	@Test
        void testDbConnectionInjection() {
            var expected = dbConnectionMock;
            sut.setConnection(dbConnectionMock);
            var result = sut.getConnection();

            assertEquals(expected, result);
        }

        @Test
        void testMapperInjection() {
            var expected = loginResponseMapperMock;
            sut.setMapper(loginResponseMapperMock);
            var result = sut.getMapper();

            assertEquals(expected, result);
        }
    }
    
    @Nested
    @DisplayName("`getUser` tests")
    public class GetUserTests {
    	
    	@Test
        void testGetUserCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(loginResponseMapperMock.mapToDTO(resultSetMock)).thenReturn(loginResponseDto);
                
                sut.getUser(loginDto);

                verify(dbConnectionMock, times(2)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(loginResponseMapperMock, times(1)).mapToDTO(null);
                verify(preparedStatementMock, times(1)).executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }

        @Test
        void testGetUserException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new Exception()).when(preparedStatementMock).executeQuery();

                assertThrows(ServerErrorException.class, () -> sut.getUser(loginDto));
            } 
            catch (Exception e) {}
        }

        @Test
        void testGetUserSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new SQLException()).when(preparedStatementMock).executeQuery();
            	
            	assertThrows(AuthenticateException.class, () -> sut.getUser(loginDto));
            } 
            catch (Exception e) {}
        }
    }
    
    @Nested
    @DisplayName("`generateToken` tests")
    public class GenerateTokenTests {
    	
    	@Test
        void testGenerateTokenCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                
                sut.generateToken(loginDto);

                verify(dbConnectionMock, times(2)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(preparedStatementMock, times(1)).executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }

        @Test
        void testGenerateTokenException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new Exception()).when(preparedStatementMock).executeQuery();

                assertThrows(ServerErrorException.class, () -> sut.generateToken(loginDto));
            } 
            catch (Exception e) {}
        }

        @Test
        void testGenerateTokenSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new SQLException()).when(preparedStatementMock).executeUpdate();
            	
            	assertThrows(BadRequestException.class, () -> sut.generateToken(loginDto));
            } 
            catch (Exception e) {}
        }
    }
    
    @Nested
    @DisplayName("`getUserId` tests")
    public class GetUserIdTests {
    	
    	@Test
        void testGetUserIdCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
                when(resultSetMock.next()).thenReturn(true, false);
                
                sut.getUserId(TOKEN);

                verify(dbConnectionMock, times(2)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(preparedStatementMock, times(1)).executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }

        @Test
        void testGetUserIdException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new Exception()).when(preparedStatementMock).executeQuery();

                assertThrows(ServerErrorException.class, () -> sut.getUserId(TOKEN));
            } 
            catch (Exception e) {}
        }

        @Test
        void testGetUserIdSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new SQLException()).when(preparedStatementMock).executeQuery();
            	
            	assertThrows(BadRequestException.class, () -> sut.getUserId(TOKEN));
            } 
            catch (Exception e) {}
        }
    }
    
}
