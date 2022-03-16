package han.ica.dea.services;

import javax.inject.Inject;

import org.ehcache.Cache;

import han.ica.dea.persistence.cache.CacheHelper;
import han.ica.dea.persistence.dao.TrackDao;
import han.ica.dea.domain.dto.TracksDto;
import han.ica.dea.domain.interfaces.services.ITrackService;

public class TrackService extends BaseService implements ITrackService {
	
	private TrackDao trackDao;
	
	private Cache<Integer, TracksDto> trackCache;
	
	@Inject
	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}
	
	public TrackService() {
		trackCache = (Cache<Integer, TracksDto>) cache.create(CacheHelper.CacheAlias.TRAKCS_NOT_IN_PLAYLIST);
	}
	
	public TracksDto getTracksNotInPlaylist(int playlistId) {
		TracksDto tracksDto = trackCache.get(playlistId);
    	if(tracksDto != null) {
    		return tracksDto;
    	}
    	
    	tracksDto = trackDao.getTracksNotInPlaylist(playlistId);
    	trackCache.put(playlistId, tracksDto);
		return tracksDto;
    }
}
