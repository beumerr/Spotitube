package han.ica.dea.domain.dto;

import java.io.Serializable;

public class LoginResponseDto implements Serializable  {
	
	private String user;
    private String token;
   
    public LoginResponseDto() {}

    public LoginResponseDto(String user, String token) {
        this.token = token;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    @Override
    public String toString() {
        return "LoginResponseDto{token='" + token + "', user='" + token + "'}";
    }
}
