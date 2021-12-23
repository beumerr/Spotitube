package han.ica.dea.persistence.mappings.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import han.ica.dea.domain.dto.LoginResponseDto;
import han.ica.dea.domain.interfaces.data.IDataMapper;

public class LoginResponseDataMapper implements IDataMapper<LoginResponseDto> {
	
    @Override
    public LoginResponseDto mapToDTO(ResultSet rs) throws SQLException {
        rs.next();
        return new LoginResponseDto(
    		rs.getString("fullname"),
    		rs.getString("token")
		);
    }
}
