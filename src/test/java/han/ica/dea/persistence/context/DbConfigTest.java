package han.ica.dea.persistence.context;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DbConfigTest {

	private DbConfig sut;
	
    private Properties propertiesMock;
    
    @BeforeEach
    public void setup() {
    	sut = new DbConfig();
    	propertiesMock = mock(Properties.class);
    	try {
    		propertiesMock.load(
	    		Objects.requireNonNull(
					getClass()
					.getClassLoader()
					.getResourceAsStream("dbConfig.properties")
				)
			);
    		sut.setProperties(propertiesMock);
    	} catch (Exception e) {
    		fail();
        }
    }
    
    @Nested
    @DisplayName("Constructor exception tests")
    class constructorExceptions {
    	
    	@Test
        public void testConstructorIOException() {
            try {
                doThrow(new IOException()).when(propertiesMock).load(
            		Objects.requireNonNull(
        				getClass()
                        .getClassLoader()
                        .getResourceAsStream("dbConfig.properties")
                    )
        		);
                
                assertDoesNotThrow(() -> sut = new DbConfig());
            } catch (Exception e) {
            	fail();
            }
        }
        
        @Test
        public void testConstructorException() {
            try {
                doThrow(Exception.class).when(propertiesMock).load(
            		Objects.requireNonNull(
        				getClass()
                        .getClassLoader()
                        .getResourceAsStream("dbConfig.properties")
                    )
        		);
                
                assertThrows(Exception.class, () -> sut = new DbConfig());
            } catch (Exception e) {
            	assertTrue(true);
            }
        }
    }
    
    
    @Nested
    @DisplayName("Getter tests")
    class dbConfigGetterTests {
    	
    	@Test
        public void testGetUrl() {
    		var expected = propertiesMock.getProperty("url");
        	var actual = sut.getUrl();
        	
        	assertEquals(expected, actual);
        }
    	
    	@Test
        public void testGetUser() {
    		var expected = propertiesMock.getProperty("user");
        	var actual = sut.getUser();
        	
        	assertEquals(expected, actual);
        }
    	
    	@Test
        public void testGetPassword() {
    		var expected = propertiesMock.getProperty("password");
        	var actual = sut.getPassword();
        	
        	assertEquals(expected, actual);
        }
    	
    	@Test
        public void testGetDriver() {
    		var expected = propertiesMock.getProperty("driver");
        	var actual = sut.getDriver();
        	
        	assertEquals(expected, actual);
        }
    }
    
   

}
