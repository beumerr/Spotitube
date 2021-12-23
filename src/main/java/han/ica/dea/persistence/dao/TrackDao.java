package han.ica.dea.persistence.dao;

import javax.inject.Inject;

import han.ica.dea.domain.dto.TrackDto;
import han.ica.dea.domain.dto.TracksDto;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.DbConnectionException;
import han.ica.dea.domain.exceptions.ServerErrorException;
import han.ica.dea.domain.interfaces.dao.ITrackDao;
import han.ica.dea.persistence.mappings.data.TracksDataMapper;

import java.sql.*;
import java.util.logging.Level;

public class TrackDao extends BaseDao implements ITrackDao<TracksDataMapper> {

    private TracksDataMapper tracksDataMapper;
    
    @Inject 
   	@Override
   	public void setMapper(TracksDataMapper tracksDataMapper) {
       	this.tracksDataMapper = tracksDataMapper;
   	}
    
    @Override
	public TracksDataMapper getMapper() {
		return this.tracksDataMapper;
	}
    
    @Override
    public TracksDto getTracksFromPlaylist(int playlistId) {
        try {         
             String sql = "SELECT DISTINCT t.[id], t.[title], o.[name] as performer, t.[duration], t.[album], "
	            		+ "t.[playcount], t.[publicationDate], t.[description], t.[offlineAvailable] "
	            		+ "FROM [track] t, [playlisttrack] pt, [owner] o, [user] u "
	            		+ "WHERE pt.[playlistid] = ? "
	            		+ "AND pt.[trackid] = t.[id] "
	            		+ "AND t.[owner] = o.[id];";

            
            PreparedStatement ps = con.get().prepareStatement(sql);
            ps.setInt(1, playlistId);
            ResultSet rs = ps.executeQuery();            
            TracksDto tracksDto = tracksDataMapper.mapToDTO(rs);
            
            con.close();
            return tracksDto;
    	}
    	catch(SQLException e) {
    		logger.log(Level.WARNING, "Can not get tracks by playlistId and token", e);
    		throw new BadRequestException();
    	}
        catch(Exception e) {
        	logger.log(Level.SEVERE, "Got internal error on `getTracksFromPlaylist` call", e);
        	throw new ServerErrorException();
        }
    }
    
    @Override
    public TracksDto getTracksNotInPlaylist(int playlistId) {
    	try {
        	String sql = "SELECT DISTINCT t.[id], t.[title], o.[name] as performer, t.[duration], t.[album], "
            		+ "t.[playcount], t.[publicationDate], t.[description], t.[offlineAvailable] "
            		+ "FROM [track] t, [playlisttrack] pt, [owner] o, [user] u "
            		+ "WHERE t.[id] NOT IN ("
    	        		+ "SELECT t.[id] "
    	        		+ "FROM [track] t, [playlisttrack] pt "
    	        		+ "WHERE t.[id] = pt.[trackid] "
    	        		+ "AND pt.[playlistid] = ?"
            		+ ")"
            		+ "AND pt.[trackid] = t.[id] "
            		+ "AND t.[owner] = o.[id];";
            
            PreparedStatement ps = con.get().prepareStatement(sql);
            ps.setInt(1, playlistId);
            ResultSet rs = ps.executeQuery();
            TracksDto tracks = tracksDataMapper.mapToDTO(rs);
            
            con.close();
            return tracks;
    	}
    	catch(SQLException e) {
    		logger.log(Level.WARNING, "Can not get tracks by playlistId and token", e);
    		throw new BadRequestException();
    	}
    	catch(Exception e) {
        	logger.log(Level.SEVERE, "Got internal error on `getTracksNotInPlaylist` call", e);
        	throw new ServerErrorException();
        }
    }
    
    @Override
    public void addTrackToPlaylist(int playlistId, TrackDto track) {
    	 try {
             String playlistSQL = "INSERT INTO [playlisttrack] ([playlistid], [trackid]) VALUES (?, ?);";
             String tracksSQL = "UPDATE [track] SET [offlineAvailable] = ? WHERE [id] = ?;";
             
             con.get().setAutoCommit(false);
                   
             PreparedStatement userlistPs = con.get().prepareStatement(tracksSQL);
             userlistPs.setBoolean(1, track.getOfflineAvailable());
             userlistPs.setInt(2, track.getId());
             userlistPs.executeUpdate();
             
             PreparedStatement playlistPs = con.get().prepareStatement(playlistSQL);
             playlistPs.setInt(1, playlistId);
             playlistPs.setInt(2, track.getId());
             playlistPs.executeUpdate();
             
             con.get().commit();
             con.close();
     	}
     	catch(SQLException e) {
     		logger.log(Level.WARNING, "Can not add track to playlist", e);

             if(con.get() != null) {
             	try {
                     con.get().rollback();
                     throw new BadRequestException();
                   } catch (SQLException ex) {
                 	  logger.log(Level.SEVERE, "Can not rollback AddTrackToPlaylist", ex);
                   }
             }
             else {
            	 throw new DbConnectionException();
             }
     	}
    	 catch(Exception e) {
         	logger.log(Level.SEVERE, "Got internal error on `addTrackToPlaylist` call", e);
         	throw new ServerErrorException();
         }
    }
    
    @Override
    public void deleteTrackFromPlaylist(int playlistId, int trackId) {
    	try {
        	String sql = "DELETE FROM [playlisttrack]  "
            		   + "WHERE [playlistid] = ? "
            		   + "AND [trackid] = ?; ";
            PreparedStatement ps = con.get().prepareStatement(sql);
            ps.setInt(1, playlistId);
            ps.setInt(2, trackId);
            ps.executeUpdate();
            
            con.close();
    	}
    	catch(SQLException e) {
    		logger.log(Level.WARNING, "Can not delete track from playlist", e);
    		throw new BadRequestException();
    	}
    	catch(Exception e) {
         	logger.log(Level.SEVERE, "Got internal error on `deleteTrackFromPlaylist` call", e);
         	throw new ServerErrorException();
         }
    }

	
   
}
