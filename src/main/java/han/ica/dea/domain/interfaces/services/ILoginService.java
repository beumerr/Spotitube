package han.ica.dea.domain.interfaces.services;

import han.ica.dea.domain.dto.LoginDto;
import han.ica.dea.domain.dto.LoginResponseDto;

public interface ILoginService {

    /**
     * Validate user and update token
     *
     * @param loginDto The DTO containing a username and password
     * @return A LoginResponseDto containing a user full name and token 
     */
	LoginResponseDto validateUser(LoginDto loginDto);
	
}