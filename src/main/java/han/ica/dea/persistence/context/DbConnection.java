package han.ica.dea.persistence.context;

import javax.inject.Inject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

	private Logger logger = Logger.getLogger(getClass().getName());
	
    private Connection connection;
    
    private DbConfig dbConfig;
    
    private boolean isDriverSet = false;
    
    @Inject
    public void setDatabaseProperties(DbConfig dbConfig) { 
    	this.dbConfig = dbConfig; 
	}

    public Connection get() {
    	try {
    		if(connection == null || connection.isClosed() || !isDriverSet) {
    			init();
        	}
    	}
    	catch(SQLException e) {
            logger.log(Level.SEVERE, "Can not get connection", e);
    	}
    	
        return connection;
    }

    private void init() {
    	if(!isDriverSet) {
    		this.loadDriver();
    	}
        try {
            connection = DriverManager.getConnection(
        		dbConfig.getUrl(),
        		dbConfig.getUser(),
        		dbConfig.getPassword()
    		);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Can not connect to database", e);
        } 
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Can not close database connection", e);
        }
    }
    
    private void loadDriver() {
    	try {
    		Class.forName(dbConfig.getDriver())
    			.getConstructor()
    			.newInstance();
    		
    		isDriverSet = true;
    	}
    	catch(Exception e) {
    		isDriverSet = false;
    		logger.log(Level.SEVERE, "Can not set database driver", e);
    	}
    }

}
