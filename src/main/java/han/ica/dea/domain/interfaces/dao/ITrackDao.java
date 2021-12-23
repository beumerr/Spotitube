package han.ica.dea.domain.interfaces.dao;

import han.ica.dea.domain.dto.TrackDto;
import han.ica.dea.domain.dto.TracksDto;

/**
 * CRUD track actions
 *
 * @type T The track data mapper class 
 */
public interface ITrackDao<T> extends IBaseMapper<T> {

    /**
     * Get all tracks from playlist 
     *
     * @param playlistId The playlist ID used to get tracks from
     * @return A TracksDto containing all tracks from corresponding playlist ID
     */
	TracksDto getTracksFromPlaylist(int playlistId);
	
	/**
     * Get all tracks not in playlist 
     *
     * @param playlistId The playlist ID used not to get tracks from
     * @return A String with the updated token
     */
	TracksDto getTracksNotInPlaylist(int playlistId);
	
	/**
     * Add track to playlist
     *
     * @param playlistId The token used to match user
     * @param track The track DTO containing data
     */
	void addTrackToPlaylist(int playlistId, TrackDto track);
	
	/**
     * Delete track from playlist
     *
     * @param playlistId The playlist ID to delete track from
     * @param trackId The track ID to delete from playlist
     */
	void deleteTrackFromPlaylist(int playlistId, int trackId);
}