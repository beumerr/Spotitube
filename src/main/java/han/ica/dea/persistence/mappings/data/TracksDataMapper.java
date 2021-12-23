package han.ica.dea.persistence.mappings.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import han.ica.dea.domain.dto.TrackDto;
import han.ica.dea.domain.dto.TracksDto;
import han.ica.dea.domain.interfaces.data.IDataMapper;

public class TracksDataMapper implements IDataMapper<TracksDto> {
	
    @Override
    public TracksDto mapToDTO(ResultSet rs) throws SQLException {
        ArrayList<TrackDto> tracks = new ArrayList<>();
        while (rs.next()) {
            tracks.add(
        		new TrackDto(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("performer"),
                    rs.getInt("duration"),
                    rs.getString("album"),
                    rs.getInt("playcount"),
                    rs.getString("publicationDate"),
                    rs.getString("description"),
                    rs.getBoolean("offlineAvailable")
				)
    		);
        }
        return new TracksDto(tracks);
    }
}
