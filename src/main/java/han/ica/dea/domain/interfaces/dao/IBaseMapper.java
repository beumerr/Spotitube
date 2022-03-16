package han.ica.dea.domain.interfaces.dao;

import javax.inject.Inject;

public abstract interface IBaseMapper<T>  {
	
	 /**
     * Set data mapper (to convert DB results into objects)
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