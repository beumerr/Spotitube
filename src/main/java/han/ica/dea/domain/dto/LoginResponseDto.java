package han.ica.dea.domain.dto;

public class LoginResponseDto {
	
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
}
