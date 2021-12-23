package han.ica.dea.domain.interfaces.data;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Map database results to object
 *
 * @type The DTO to map to
 */
public interface IDataMapper<T> {
	
	 /**
     * Set data mapper to convert DB result set to object
     *
     * @param userId The user ID to get playlists from
     * @return A PlaylistsDto containing all user playlists
     */
    T mapToDTO(ResultSet rs) throws SQLException;
}
