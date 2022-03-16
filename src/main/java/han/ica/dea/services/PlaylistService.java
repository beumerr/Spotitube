package han.ica.dea.services;

import javax.inject.Inject;

import org.ehcache.Cache;

import han.ica.dea.persistence.cache.CacheHelper;
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
	
	private Cache<Integer, TracksDto> trackCache;
	private Cache<Integer, TracksDto> tracksNotInPlaylist;
	private Cache<Integer, PlaylistsDto> playlistsCache;
	
	@Inject
	public void setPlaylistDao(PlaylistDao playlistDao) {
		this.playlistDao = playlistDao;
	}
	
	@Inject
	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}
	  
	public PlaylistService() {
		trackCache = cache.create(CacheHelper.CacheAlias.TRACKS);
		tracksNotInPlaylist = cache.create(CacheHelper.CacheAlias.TRAKCS_NOT_IN_PLAYLIST);
		playlistsCache = cache.create(CacheHelper.CacheAlias.PLAYLISTS);
	}
	
	/*--- PLAYLIST ---*/
	
	public PlaylistsDto getAllPlaylists(int userId) {
        PlaylistsDto playlists = playlistsCache.get(userId);
    	if(playlists != null) {
    		return playlists;
    	}
    	
    	playlists = playlistDao.getAllPlaylists(userId);
    	playlistsCache.put(userId, playlists);
		return playlists;
    }
	
	public PlaylistsDto addPlaylist(PlaylistDto playlist, int userId) {
		playlistDao.addPlaylist(userId, playlist);
        return GetPlaylists(userId);
	}
	
	public PlaylistsDto deletePlaylist(int id, int userId) {
    	playlistDao.deletePlaylist(id);
    	return GetPlaylists(userId);
    }
	
	public PlaylistsDto putPlaylist(PlaylistDto playlist, int id, int userId) {
    	playlistDao.putPlaylist(id, playlist);
    	return GetPlaylists(userId);
    }
	
	private PlaylistsDto GetPlaylists(int id) {
		PlaylistsDto playlistsDto = playlistDao.getAllPlaylists(id);
    	playlistsCache.put(id, playlistsDto);
    	return getAllPlaylists(id);
	}
	
	/*--- PLAYLIST TRACKS ---*/
	
	public TracksDto getTracksFromPlaylist(int id) {
		TracksDto tracks = trackCache.get(id);
    	if(tracks != null) {
    		return tracks;
    	}
    	
    	tracks = trackDao.getTracksFromPlaylist(id);
    	trackCache.put(id, tracks);
		return tracks;
    }
	
	public TracksDto addTrackToPlaylist(int userId, int playlistId, TrackDto track) {
        trackDao.addTrackToPlaylist(playlistId, track);
        return GetTracks(playlistId);
    }
	
	public TracksDto deleteTrackFromPlaylist(int playlistId, int trackId) {
        trackDao.deleteTrackFromPlaylist(playlistId, trackId);
        return GetTracks(playlistId);
    }
	
	private TracksDto GetTracks(int playlistId) {
		TracksDto tracksDto = trackDao.getTracksFromPlaylist(playlistId);
		trackCache.put(playlistId, tracksDto);
		tracksNotInPlaylist.clear();
    	return getTracksFromPlaylist(playlistId);
	}
	
}
