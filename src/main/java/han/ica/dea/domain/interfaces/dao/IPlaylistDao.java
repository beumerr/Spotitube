package han.ica.dea.domain.interfaces.dao;

import han.ica.dea.domain.dto.PlaylistDto;
import han.ica.dea.domain.dto.PlaylistsDto;

/**
 * CRUD playlist actions
 *
 * @type T The playlist data mapper class 
 */
public interface IPlaylistDao<T> extends IBaseMapper<T> {
	
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
	void addPlaylist(int userId, PlaylistDto playlistDto);
	
	/**
     * Delete playlist
     *
     * @param playlistId The playlist ID to delete
     */
	void deletePlaylist(int playlistId);
	
	/**
     * Update playlist
     *
     * @param playlistId The playlist to update
     * @param userId The playlist DTO to update
     */
	void putPlaylist(int playlistId, PlaylistDto playlist);
}