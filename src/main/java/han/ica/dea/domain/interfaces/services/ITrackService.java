package han.ica.dea.domain.interfaces.services;

import han.ica.dea.domain.dto.TracksDto;

public interface ITrackService {

	 /**
     * Get all tracks not in playlist 
     *
     * @param playlistId The playlist ID used to not get tracks from
     * @return A TracksDto containing all tracks not in corresponding playlist ID
     */
	TracksDto getTracksNotInPlaylist(int playlistId);
	
}