package han.ica.dea.services;

import javax.inject.Inject;

import org.ehcache.Cache;

import han.ica.dea.persistence.cache.CacheHelper;
import han.ica.dea.persistence.dao.LoginDao;
import han.ica.dea.domain.dto.LoginDto;
import han.ica.dea.domain.dto.LoginResponseDto;
import han.ica.dea.domain.exceptions.MissingTokenException;
import han.ica.dea.domain.interfaces.services.ILoginService;

public class LoginService extends BaseService implements ILoginService {

	private LoginDao loginDao;
	
	private Cache<String, LoginResponseDto> sessionCache;
	
	@Inject
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	public LoginService() {
		sessionCache = cache.create(CacheHelper.CacheAlias.SESSION);
	}
	
	@Override
	public LoginResponseDto validateUser(LoginDto loginDto) {
		LoginResponseDto loginResponseDto = loginDao.getUser(loginDto);
		
		if(loginResponseDto != null) {
			LoginResponseDto cacheSession = sessionCache.get(loginResponseDto.getToken());
			if(cacheSession != null) {
				return cacheSession;
			}
			
			String token = loginDao.generateToken(loginDto);
			loginResponseDto.setToken(token);
			sessionCache.put(token, loginResponseDto);
		}
		else {
			throw new MissingTokenException();
		}
		
        return loginResponseDto;
	}

	
}
