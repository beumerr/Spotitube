package han.ica.dea.services;

import javax.inject.Inject;

import han.ica.dea.persistence.dao.LoginDao;
import han.ica.dea.domain.dto.LoginDto;
import han.ica.dea.domain.dto.LoginResponseDto;
import han.ica.dea.domain.exceptions.MissingTokenException;
import han.ica.dea.domain.interfaces.services.ILoginService;

public class LoginService implements ILoginService {

	private LoginDao loginDao;
	
	@Inject
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	@Override
	public LoginResponseDto validateUser(LoginDto loginDto) {
		LoginResponseDto loginResponseDto = loginDao.getUser(loginDto);
		
		if(loginResponseDto != null) {
			String token = loginDao.generateToken(loginDto);
			loginResponseDto.setToken(token);
		}
		else {
			throw new MissingTokenException();
		}
		
        return loginResponseDto;
	}

	
}
