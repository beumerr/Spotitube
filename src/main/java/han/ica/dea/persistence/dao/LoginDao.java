package han.ica.dea.persistence.dao;

import han.ica.dea.domain.dto.LoginDto;
import han.ica.dea.domain.dto.LoginResponseDto;
import han.ica.dea.domain.exceptions.AuthenticateException;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.ServerErrorException;
import han.ica.dea.domain.interfaces.dao.ILoginDao;
import han.ica.dea.persistence.mappings.data.LoginResponseDataMapper;

import javax.inject.Inject;
import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;

public class LoginDao extends BaseDao implements ILoginDao<LoginResponseDataMapper> {
	
    private LoginResponseDataMapper loginResponseDataMapper;
    
    @Inject
    @Override
    public void setMapper(LoginResponseDataMapper loginResponseDataMapper) {
        this.loginResponseDataMapper = loginResponseDataMapper;
    }
    
    @Override
	public LoginResponseDataMapper getMapper() {
		return this.loginResponseDataMapper;
	}
    
    @Override
    public LoginResponseDto getUser(LoginDto loginDto) {    
        try {
    		String sql = "SELECT [fullname], [token] FROM [user] "
    				   + "WHERE [user] = ? "
    				   + "AND password = ?;";
    		
            PreparedStatement ps = con.get().prepareStatement(sql);
            ps.setString(1, loginDto.getUser());
            ps.setString(2, loginDto.getPassword());
            ResultSet result = ps.executeQuery(); 
            LoginResponseDto loginResponseDto;
            loginResponseDto = loginResponseDataMapper.mapToDTO(result);
            
            con.close();
            return loginResponseDto;
    	}
        catch (SQLException e) {
        	logger.log(Level.INFO, "Got SQL error on `getUser` request", e);
        	throw new AuthenticateException();
        }
        catch(Exception e) {
        	logger.log(Level.SEVERE, "Got internal error on `getUser` request", e);
        	throw new ServerErrorException();
        }
    }
    
    @Override
    public String generateToken(LoginDto loginDTO) {
    	try {
            String sql = "UPDATE [user] "
            		   + "SET [token] = ? "
            		   + "WHERE [user] = ?;";
            
            PreparedStatement ps = con.get().prepareStatement(sql);
            String token = UUID.randomUUID().toString();
            ps.setString(1, token);
            ps.setString(2, loginDTO.getUser());
            ps.executeUpdate();
            
            con.close();
            return token;
    	}
    	catch(SQLException e) {
    		logger.log(Level.INFO, "Can not generate token for user", e);
    		throw new BadRequestException();
    	}
    	catch(Exception e) {
        	logger.log(Level.SEVERE, "Got internal error on `generateToken` request", e);
        	throw new ServerErrorException();
        }
    }
    
    @Override
    public int getUserId(String token) {    
        try {
    		String sql = "SELECT [id]"
    				   + "FROM [user] "
    				   + "WHERE [token] = ?;";
    		
            PreparedStatement ps = con.get().prepareStatement(sql);
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery(); 
            rs.next();
            int userId = rs.getInt("id");
            
            con.close();
            return userId;
    	}
        catch (SQLException e) {
        	logger.log(Level.INFO, "Can not validate user token", e);
            throw new BadRequestException();
        }
        catch(Exception e) {
        	logger.log(Level.SEVERE, "Got internal error on `getUserId` request", e);
        	throw new ServerErrorException();
        }
    }
}
