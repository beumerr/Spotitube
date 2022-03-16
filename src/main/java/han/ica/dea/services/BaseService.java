package han.ica.dea.services;

import java.util.logging.Logger;

import han.ica.dea.persistence.cache.CacheHelper;

public abstract class BaseService {
	
	protected Logger logger = Logger.getLogger(getClass().getName());
	
	protected CacheHelper cache = CacheHelper.get();
}
