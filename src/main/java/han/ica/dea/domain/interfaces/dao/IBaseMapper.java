package han.ica.dea.domain.interfaces.dao;

import javax.inject.Inject;

public abstract interface IBaseMapper<T>  {
	
	 /**
     * Set data mapper to convert DB result set to object
     *
     * @param userId The user ID to get playlists from
     */
	@Inject void setMapper(T dataMapper);
	
	 /**
     * Get data mapper
     *
     * @return A datamapper instance
     */
	T getMapper();
	
}