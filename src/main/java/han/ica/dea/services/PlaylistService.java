package han.ica.dea.services;

import javax.inject.Inject;

import han.ica.dea.persistence.dao.PlaylistDao;
import han.ica.dea.persistence.dao.TrackDao;
import han.ica.dea.domain.dto.PlaylistDto;
import han.ica.dea.domain.dto.PlaylistsDto;
import han.ica.dea.domain.dto.TrackDto;
import han.ica.dea.domain.dto.TracksDto;
import han.ica.dea.domain.interfaces.services.IPlaylistService;

public class PlaylistService extends BaseService implements IPlaylistService {

	private PlaylistDao playlistDao;
	
	private TrackDao trackDao;
	
	@Inject
	public void setPlaylistDao(PlaylistDao playlistDao) {
		this.playlistDao = playlistDao;
	}
	
	@Inject
	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}
	
	/*--- PLAYLIST ---*/
	
	public PlaylistsDto getAllPlaylists(int userId) {
        return playlistDao.getAllPlaylists(userId);
    }
	
	public PlaylistsDto addPlaylist(PlaylistDto playlist, int userId) {
		playlistDao.addPlaylist(userId, playlist);
        return playlistDao.getAllPlaylists(userId);
	}
	
	public PlaylistsDto deletePlaylist(int id, int userId) {
    	playlistDao.deletePlaylist(id);
    	return playlistDao.getAllPlaylists(userId);

    }
	
	public PlaylistsDto putPlaylist(PlaylistDto playlist, int id, int userId) {
    	playlistDao.putPlaylist(id, playlist);
    	return playlistDao.getAllPlaylists(userId);
    }
	
	/*--- PLAYLIST TRACKS ---*/
	
	public TracksDto getTracksFromPlaylist(int id, int userId) {
		return trackDao.getTracksFromPlaylist(id);
    }
	
	public TracksDto addTrackToPlaylist(int userId, int playlistId, TrackDto track) {
        trackDao.addTrackToPlaylist(playlistId, track);
        return trackDao.getTracksFromPlaylist(playlistId);
    }
	
	public TracksDto deleteTrackFromPlaylist(int playlistId, int trackId) {
        trackDao.deleteTrackFromPlaylist(playlistId, trackId);
        return trackDao.getTracksFromPlaylist(playlistId);
    }
	
}
