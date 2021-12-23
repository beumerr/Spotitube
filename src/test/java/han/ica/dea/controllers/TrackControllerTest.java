package han.ica.dea.controllers;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.ServerErrorException;
import han.ica.dea.services.TrackService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrackControllerTest {

    private TrackController sut;
    
    private TrackService trackServiceMock; 
    
	private int userId = 1;
	private int playlistId = 1;
	
    @BeforeEach
    public void setup() {
        sut = new TrackController();
        trackServiceMock = Mockito.mock(TrackService.class);
        sut.setTrackService(trackServiceMock);
    }
    
    @Nested
    @DisplayName("Injection Tests")
    class InjectionTests {
    	
    	@Test
        public void testPlaylistServiceInjection() {
            var expected = trackServiceMock;
            var result = sut.getTrackService();
            
            assertEquals(expected, result);
        }
        
        @Test
        public void testSetLoginService() {
            var expected = trackServiceMock;
            sut.setTrackService(trackServiceMock);
            var result = sut.getTrackService();
            
            assertEquals(expected, result);
        }
    }
    
    /*--- TRACK - GetTracksByPlaylist ---*/
    
    @Nested
    @DisplayName("`GetTracksByPlaylist` Tests")
    class GetTracksByPlaylistTests {
    	
    	@Test
        public void testGetTracksByPlaylistResponseOk() {
            var expected = Response.Status.OK;
            var result = sut.getTracksByPlaylist(playlistId, userId);

            assertEquals(expected.getStatusCode(), result.getStatus());
        }
        
        @Test
        public void testGetTracksByPlaylistBadRequestException() {
            var expected = new BadRequestException();
            when(trackServiceMock.getTracksNotInPlaylist(playlistId)).thenThrow(expected);

            assertThrows(BadRequestException.class, () -> sut.getTracksByPlaylist(playlistId, userId));
        }
        
        @Test
        public void testGetTracksByPlaylistServerErrorException() {
            var expected = new ServerErrorException();
            when(trackServiceMock.getTracksNotInPlaylist(playlistId)).thenThrow(expected);

            assertThrows(ServerErrorException.class, () -> sut.getTracksByPlaylist(playlistId, userId));
        }
        
        @Test
        public void testGetTracksByPlaylistCallsGetTracksByPlaylist() {
            sut.getTracksByPlaylist(playlistId, userId);

            verify(trackServiceMock, times(1)).getTracksNotInPlaylist(playlistId);
        }
    }
}



















