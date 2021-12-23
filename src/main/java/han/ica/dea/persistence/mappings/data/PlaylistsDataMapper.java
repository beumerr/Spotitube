package han.ica.dea.persistence.mappings.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import han.ica.dea.domain.dto.PlaylistDto;
import han.ica.dea.domain.interfaces.data.IDataMapper;

public class PlaylistsDataMapper implements IDataMapper<ArrayList<PlaylistDto>> {
	
    @Override
    public ArrayList<PlaylistDto> mapToDTO(ResultSet rs) throws SQLException {
        ArrayList<PlaylistDto> playlists = new ArrayList<>();
        while (rs.next()) {
            playlists.add(
        		new PlaylistDto(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getBoolean("isowner")
                )
    		);
        }
        return playlists;
    }
}
