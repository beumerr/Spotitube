package han.ica.dea.persistence.context;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionTest {
	
	private DbConnection sut;
	
    private Connection connection;
   
    private DbConfig dbConfigMock;
    
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=Spotitube";
    private static final String USER = "sa";
    private static final String PASSWORD = "dbrules";
    
    @BeforeEach
    public void setup() {
    	sut = new DbConnection();
    	dbConfigMock = mock(DbConfig.class);;
    	sut.setDatabaseProperties(dbConfigMock);
    	
    	try {
    		when(dbConfigMock.getDriver()).thenReturn(DRIVER);
            when(dbConfigMock.getUrl()).thenReturn(URL);
            when(dbConfigMock.getUser()).thenReturn(USER);
            when(dbConfigMock.getPassword()).thenReturn(PASSWORD);
            
    		connection = DriverManager.getConnection(
				dbConfigMock.getUrl(), 
				dbConfigMock.getUser(), 
				dbConfigMock.getPassword()
			);
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    @Nested
    @DisplayName("Get connection")
    class GetConnectionTests {
    	
    	@Test
        public void testGetCalls() {
        	try {
                sut.get();

                verify(dbConfigMock).getDriver();
                verify(dbConfigMock, times(2)).getUrl();
                verify(dbConfigMock, times(2)).getUser();
                verify(dbConfigMock, times(2)).getPassword();
            } catch (Exception e) {
            	fail();
            }
        }
        
        @Test
        public void testGetReturn() {
        	try {
        		var expected = connection.getClass();
                var actual =  sut.get().getClass();
                
                assertNotNull(sut.get());
                assertEquals(expected, actual);
            } catch (Exception e) {
            	fail();
            }
        }
    }
    
    @Test
    @DisplayName("Close connection")
    public void testClose() {
    	try {
    		var expected = sut.get();
            sut.close();

            assertTrue(expected.isClosed());
    	}
    	catch(Exception e) {
    		fail();
    	}	
    }


}
