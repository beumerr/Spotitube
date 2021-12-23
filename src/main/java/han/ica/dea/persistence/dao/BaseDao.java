package han.ica.dea.persistence.dao;

import javax.inject.Inject;

import han.ica.dea.domain.interfaces.dao.IBaseDao;
import han.ica.dea.persistence.context.DbConnection;

import java.util.logging.Logger;

public abstract class BaseDao implements IBaseDao<DbConnection> {

	protected Logger logger = Logger.getLogger(getClass().getName());
	
	protected DbConnection con;

    @Inject
    @Override
    public void setConnection(DbConnection dbConnection) {
        this.con = dbConnection;
    }
    
    @Override
    public DbConnection getConnection() {
        return this.con;
    }

}
