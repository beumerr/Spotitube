package han.ica.dea.domain.interfaces.dao;

import javax.inject.Inject;

import han.ica.dea.persistence.context.DbConnection;

public interface IBaseDao<T> {

    /**
     * Set Database connection 
     *
     * @param T Connection class
     */
	@Inject void setConnection(T dbConnection);
	
	/**
     * Get Database connection service
     *
     * @return DbConnection instance
     */
	@Inject DbConnection getConnection();
}