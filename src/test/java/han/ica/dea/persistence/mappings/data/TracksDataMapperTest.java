package han.ica.dea.persistence.mappings.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import han.ica.dea.domain.dto.TrackDto;

public class TracksDataMapperTest {
	
	private TracksDataMapper sut;
    private ResultSet mockedResultSet;

    private int id = 1;
    private String title = "title";
    private String performer = "performer";
    private int duration = 2;
    private String album = "album";
    private int playcount = 3;
    private String publicationDate = "publicationDate";
    private String description = "description";
    private boolean offlineAvailable = true;

    @BeforeEach
    public void setup() {
        sut = new TracksDataMapper();
        mockedResultSet = mock(ResultSet.class);
    }

    @Nested
    public class TracksDataMapperTests {
    	
        @Test
        public void testMapToDtoReturn() {
            try {
                var expected = new TrackDto(id, title, performer, duration, album, playcount, publicationDate, description, offlineAvailable);
                when(mockedResultSet.next()).thenReturn(true, false);
                when(mockedResultSet.getInt("id")).thenReturn(id);
                when(mockedResultSet.getString("title")).thenReturn(title);
                when(mockedResultSet.getString("performer")).thenReturn(performer);
                when(mockedResultSet.getInt("duration")).thenReturn(duration);
                when(mockedResultSet.getString("album")).thenReturn(album);
                when(mockedResultSet.getInt("playcount")).thenReturn(playcount);
                when(mockedResultSet.getString("publicationDate")).thenReturn(publicationDate);
                when(mockedResultSet.getString("description")).thenReturn(description);
                when(mockedResultSet.getBoolean("offlineAvailable")).thenReturn(offlineAvailable);

                var actual = sut.mapToDTO(mockedResultSet);

                assertEquals(expected.getId(), actual.getTracks().get(0).getId());
                assertEquals(expected.getTitle(), actual.getTracks().get(0).getTitle());
                assertEquals(expected.getPerformer(), actual.getTracks().get(0).getPerformer());
                assertEquals(expected.getDuration(), actual.getTracks().get(0).getDuration());
                assertEquals(expected.getAlbum(), actual.getTracks().get(0).getAlbum());
                assertEquals(expected.getPlaycount(), actual.getTracks().get(0).getPlaycount());
                assertEquals(expected.getPublicationDate(), actual.getTracks().get(0).getPublicationDate());
                assertEquals(expected.getDescription(), actual.getTracks().get(0).getDescription());
                assertEquals(expected.getOfflineAvailable(), actual.getTracks().get(0).getOfflineAvailable());
            } catch(SQLException e) {
                fail();
            }
        }

        @Test
        public void testMapToDtoIterations() {
            try {
                var expected = 2;
                when(mockedResultSet.next()).thenReturn(true, true, false);
                when(mockedResultSet.getInt("id")).thenReturn(id);
                when(mockedResultSet.getString("title")).thenReturn(title);
                when(mockedResultSet.getString("performer")).thenReturn(performer);
                when(mockedResultSet.getInt("duration")).thenReturn(duration);
                when(mockedResultSet.getString("album")).thenReturn(album);
                when(mockedResultSet.getInt("playcount")).thenReturn(playcount);
                when(mockedResultSet.getString("publicationDate")).thenReturn(publicationDate);
                when(mockedResultSet.getString("description")).thenReturn(description);
                when(mockedResultSet.getBoolean("offlineAvailable")).thenReturn(offlineAvailable);

                var value = sut.mapToDTO(mockedResultSet);
                var actual = value.getTracks().size();

                assertEquals(expected, actual);

            } catch(SQLException e) {
                fail();
            }
        }
    }
}
