package han.ica.dea.services;

import javax.inject.Inject;

import han.ica.dea.persistence.dao.TrackDao;
import han.ica.dea.domain.dto.TracksDto;
import han.ica.dea.domain.interfaces.services.ITrackService;

public class TrackService extends BaseService implements ITrackService {
	
	private TrackDao trackDao;
	
	@Inject
	public void setTrackDao(TrackDao trackDao) {
		this.trackDao = trackDao;
	}
	
	public TracksDto getTracksNotInPlaylist(int playlistId) {
    	return trackDao.getTracksNotInPlaylist(playlistId);
    }
}
