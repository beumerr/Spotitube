package han.ica.dea.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;

import han.ica.dea.persistence.dao.TrackDao;
import han.ica.dea.domain.dto.TracksDto;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.ServerErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TrackServiceTest {

    private TrackService sut;

    private TracksDto tracksDto;
    
    private TrackDao trackDaoMock;
    
    private static int playlistId = 1;

    @BeforeEach
    public void setup(final TestInfo info) {
        sut = new TrackService();
        tracksDto = new TracksDto();;
        trackDaoMock = Mockito.mock(TrackDao.class);
        sut.setTrackDao(trackDaoMock);
    }

    /*--- TRACK - GetTracksByPlaylist ---*/
    
    @Nested
    @DisplayName("`GetTracksByPlaylist` Tests")
    class GetTracksByPlaylistTests {
    	
    	@Test
        public void testGetTracksByPlaylist() {
        	var expected = tracksDto;
        	when(trackDaoMock.getTracksNotInPlaylist(playlistId)).thenReturn(expected);
            var result = sut.getTracksNotInPlaylist(playlistId);
            
            assertEquals(expected, result);
        }
        
        @Test
        public void testGetTracksByPlaylistBadRequestException() {
        	var expected = new BadRequestException();
        	when(trackDaoMock.getTracksNotInPlaylist(playlistId)).thenThrow(expected);

        	assertThrows(BadRequestException.class, () -> sut.getTracksNotInPlaylist(playlistId));
        }
        
        @Test
        public void testGetTracksByPlaylistException() {
        	var expected = new ServerErrorException();
        	when(trackDaoMock.getTracksNotInPlaylist(playlistId)).thenThrow(expected);

        	assertThrows(ServerErrorException.class, () -> sut.getTracksNotInPlaylist(playlistId));
        }
        
        @Test
        public void testGetTracksByPlaylistCalls() {
        	sut.getTracksNotInPlaylist(playlistId);

            verify(trackDaoMock, times(1)).getTracksNotInPlaylist(playlistId);
        }
    }
    
}



















