package han.ica.dea.domain.interfaces.dao;

import han.ica.dea.domain.dto.LoginDto;
import han.ica.dea.domain.dto.LoginResponseDto;

public interface ILoginDao<T> extends IBaseMapper<T> {

    /**
     * Get user by username and password
     *
     * @param loginDto The DTO containing a username and password
     * @return A LoginResponseDto containing a user full name and token 
     */
	LoginResponseDto getUser(LoginDto loginDto);
	
	/**
     * Update user token
     *
     * @param loginDto The DTO containing a username 
     * @return A String with the updated token
     */
	String generateToken(LoginDto loginDto);
	
	/**
     * Get user ID based on token
     *
     * @param token The token used to match user
     * @return The corresponding user id 
     */
	int getUserId(String token);
}