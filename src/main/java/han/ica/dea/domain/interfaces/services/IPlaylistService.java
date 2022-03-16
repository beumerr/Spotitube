package han.ica.dea.domain.interfaces.services;

import han.ica.dea.domain.dto.PlaylistDto;
import han.ica.dea.domain.dto.PlaylistsDto;
import han.ica.dea.domain.dto.TrackDto;
import han.ica.dea.domain.dto.TracksDto;

public interface IPlaylistService {

	/**
     * Get all playlists from user
     *
     * @param userId The user ID to get playlists from
     * @return A PlaylistsDto containing all user playlists
     */
	PlaylistsDto getAllPlaylists(int userId);
	
	/**
     * Add playlist to user
     *
     * @param userId The user ID to add playlist to
     * @param playlistDto The playlist DTO to add
     */
	PlaylistsDto addPlaylist(PlaylistDto playlist, int userId);
	
	/**
     * Delete playlist
     *
     * @param playlistId The playlist ID to delete
     */
	PlaylistsDto deletePlaylist(int id, int userId);
	
	/**
     * Update playlist
     *
     * @param playlistId The playlist to update
     * @param userId The playlist DTO to update
     */
	PlaylistsDto putPlaylist(PlaylistDto playlist, int id, int userId);
	
	 /**
     * Get all tracks from playlist 
     *
     * @param playlistId The playlist ID used to get tracks from
     * @return A TracksDto containing all tracks from corresponding playlist ID
     */
	TracksDto getTracksFromPlaylist(int id);
	
	/**
     * Add track to playlist
     *
     * @param playlistId The token used to match user
     * @param track The track DTO containing data
     */
	TracksDto addTrackToPlaylist(int userId, int playlistId, TrackDto track);
	
	/**
     * Delete track from playlist
     *
     * @param playlistId The playlist ID to delete track from
     * @param trackId The track ID to delete from playlist
     */
	TracksDto deleteTrackFromPlaylist(int playlistId, int trackId);
}