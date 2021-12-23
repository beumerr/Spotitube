package han.ica.dea.persistence.context;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConfig {

    private Logger logger = Logger.getLogger(getClass().getName());
    private Properties properties;

    public DbConfig() {
        try {
        	properties = new Properties();
            properties.load(
	    		Objects.requireNonNull(
    				getClass()
    				.getClassLoader()
    				.getResourceAsStream("dbConfig.properties")
				)
    		);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Can't access dbConfig.properties", e);
        }
    }

    public String getUrl() { 
    	return properties.getProperty("url"); 
	}
    
    public String getUser() { 
    	return properties.getProperty("user"); 
	}

    public String getPassword() { 
    	return properties.getProperty("password"); 
	}

    public String getDriver() {
        return properties.getProperty("driver");
    }
    
    public void setProperties(Properties properties) {
    	this.properties = properties;
    }

}
