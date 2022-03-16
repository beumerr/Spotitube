package han.ica.dea.persistence.dao;

import han.ica.dea.domain.dto.PlaylistDto;
import han.ica.dea.domain.dto.PlaylistsDto;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.DbConnectionException;
import han.ica.dea.domain.exceptions.ServerErrorException;
import han.ica.dea.domain.interfaces.dao.IPlaylistDao;
import han.ica.dea.persistence.mappings.data.PlaylistsDataMapper;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class PlaylistDao extends BaseDao implements IPlaylistDao<PlaylistsDataMapper> {

    private PlaylistsDataMapper playlistsDataMapper;
    
	@Inject
	@Override
	public void setMapper(PlaylistsDataMapper dataMapper) {
    	this.playlistsDataMapper = dataMapper;
	}
	
	@Override
	public PlaylistsDataMapper getMapper() {
		return this.playlistsDataMapper;
	}
    
    @Override
	public PlaylistsDto getAllPlaylists(int userId) {
        try {
            String sql = "SELECT DISTINCT p.[id], p.[name], up.[isowner] "
	            		+ "FROM [playlist] p, [userplaylist] up, [user] u "
	            		+ "WHERE u.[id] = ? "
	            		+ "AND u.[id] = up.[userid] "
	            		+ "AND p.[id] = up.[playlistid];";
            
            PreparedStatement ps = con.get().prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            ArrayList<PlaylistDto> playlists = playlistsDataMapper.mapToDTO(rs);
            int playlistLength = getTotalDuration(userId);
            
            con.close();
            return new PlaylistsDto(playlists, playlistLength);
    	}
    	catch(SQLException e) {
    		logger.log(Level.WARNING, "Can not get all playlists", e);
    		throw new BadRequestException();
    	}
        catch(Exception e) {
        	logger.log(Level.SEVERE, "Got internal error on `getTotalDuration` request", e);
        	throw new ServerErrorException();
        }
    }
	
	public int getTotalDuration(int userId) {
        try {
            String sql = "SELECT sum(t.[duration]) as totalDuration "
	            	   + "FROM [playlist] p, [userplaylist] up, [user] u, [track] t, [playlisttrack] pt "
	            	   + "WHERE u.[id] = ? "
	            	   + "AND u.[id] = up.[userid] "
	            	   + "AND p.[id] = up.[playlistid] "
	            	   + "AND pt.[playlistid] = up.[playlistid] "
	            	   + "AND pt.[trackid] = t.[id];";
            
            PreparedStatement ps = con.get().prepareStatement(sql);

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            int totalDuration = 0;
            while(rs.next()) {
            	totalDuration += rs.getInt("totalDuration");
            }

            con.close();
            return totalDuration;
    	}
    	catch(SQLException e) {
    		logger.log(Level.WARNING, "Can not get playlist totalDuration", e);
    		throw new BadRequestException();
    	}
        catch(Exception e) {
        	logger.log(Level.SEVERE, "Got internal error on `getTotalDuration` request", e);
        	throw new ServerErrorException();
        }
    }
	
	@Override
	public void addPlaylist(int userId, PlaylistDto playlistDto) {
        try {
            con.get().setAutoCommit(false);
            
            String playlistSQL ="INSERT INTO [playlist]([name]) VALUES (?) ";
            String userlistSQL = "INSERT INTO [userplaylist]([userid], [playlistid], [isowner]) "
				               + "VALUES("
					               + "?, "
					               + "(SELECT TOP 1 [id] FROM [playlist] ORDER BY [id] DESC), "
				            	   + "?"
			            	   + ");";
            
            PreparedStatement playlistPs = con.get().prepareStatement(playlistSQL);
            playlistPs.setString(1, playlistDto.getName());
            playlistPs.executeUpdate();
            
            PreparedStatement userlistPs = con.get().prepareStatement(userlistSQL);
            userlistPs.setInt(1, userId);
            userlistPs.setBoolean(2, true); // client side bug? this should be; `playlistDto.isOwner()`
            userlistPs.executeUpdate();
            
            con.get().commit();
            con.close();
    	}
    	catch(SQLException e) {
    		logger.log(Level.WARNING, "Can not add playlist", e);

            if(con.get() != null) {
            	try {
                    con.get().rollback();
                    throw new BadRequestException();
                  } catch (SQLException ex) {
                	  logger.log(Level.SEVERE, "Can not rollback playlist", e);
                  }
            }
            else {
            	throw new DbConnectionException();
            }
    	}
    }
	
	@Override
	public void deletePlaylist(int id) {
		try {
	        String sql = "DELETE FROM [playlist] WHERE id = ?;";
	        
	        PreparedStatement ps = con.get().prepareStatement(sql);
	        ps.setInt(1, id);
	        ps.executeUpdate();
	        
	        con.close();
		}
		catch(SQLException e) {
			logger.log(Level.WARNING, "Can not delete playlist", e);
			throw new BadRequestException();
		}
    }
	
	@Override
	public void putPlaylist(int id, PlaylistDto playlist) {
		try {
	        String sql = "UPDATE [playlist] SET [name] = ? WHERE [id] = ?;";
	        
	        PreparedStatement ps = con.get().prepareStatement(sql);
	        ps.setString(1, playlist.getName());
	        ps.setInt(2, id);
	        ps.executeUpdate();
	        
	        con.close();
		}
		catch(SQLException e) {
			logger.log(Level.WARNING, "Can not delete playlist", e);
			throw new BadRequestException();
		}
    }

}
