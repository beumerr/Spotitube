package han.ica.dea.controllers;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;

import han.ica.dea.domain.dto.PlaylistDto;
import han.ica.dea.domain.dto.TrackDto;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.ServerErrorException;
import han.ica.dea.services.PlaylistService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

public class PlaylistControllerTest {

    private PlaylistController sut;
    
    private PlaylistService playlistServiceMock;
    
    private PlaylistDto playlistDTO = new PlaylistDto();
    
    private TrackDto trackDto = new TrackDto();
    
	private int userId = 1;
	private int playlistId = 1;
	private int trackId = 1;
	
    @BeforeEach
    public void setup(final TestInfo info) {
    	sut = new PlaylistController();
        playlistServiceMock = Mockito.mock(PlaylistService.class);
        final Set<String> testTags = info.getTags();
        if(!testTags.stream()
           .filter(tag->tag.equals("skipInjection"))
           .findFirst()
           .isPresent()){
        	
        	// Inject
        	sut.setPlaylistService(playlistServiceMock);
        }
    }
    
    @Nested
    @DisplayName("Controller injection tests")
    class InjectionTests {
    	
    	@Tag("skipInjection")
	    @Test
	    public void testSetLoginService() {
	        var expected = playlistServiceMock;
	        sut.setPlaylistService(playlistServiceMock);
	        var result = sut.getPlaylistService();
	        
	        assertNotNull(result);
	        assertEquals(expected, result);
	    }
    }
    
    /*--- PLAYLIST - GetAllPlaylists ---*/
    
    @Nested
    @DisplayName("`GetAllPlaylistsTests` tests")
    class GetAllPlaylistsTests {
    	
    	@Test
        public void testGetAllPlaylistsResponseOk() {
            var expected = Response.Status.OK;
            var result = sut.getAllPlaylists(userId);
            
            
            assertEquals(expected.getStatusCode(), result.getStatus());
        }
        
        @Test
        public void testGetAllPlaylistsBadRequestException() {
            var expected = new BadRequestException();
            when(playlistServiceMock.getAllPlaylists(userId)).thenThrow(expected);

            assertThrows(BadRequestException.class, () -> sut.getAllPlaylists(userId));
        }
        
        @Test
        public void testGetAllPlaylistsServerErrorException() {
            var expected = new ServerErrorException();
            when(playlistServiceMock.getAllPlaylists(userId)).thenThrow(expected);

            assertThrows(ServerErrorException.class, () -> sut.getAllPlaylists(userId));
        }
        
        @Test
        public void testGetAllPlaylistsCalls() {
            sut.getAllPlaylists(userId);

            verify(playlistServiceMock, times(1)).getAllPlaylists(userId);
        }
    }
    
    /*--- PLAYLIST - PostPlaylist ---*/
    
    @Nested
    @DisplayName("`PostPlaylistTests` tests")
    class PostPlaylistTests {
    	
    	@Test
	    public void testPostPlaylistResponseOk() {
	        var expected = Response.Status.OK;
	        var result = sut.getAllPlaylists(userId);

	        assertEquals(expected.getStatusCode(), result.getStatus());
	    }
	    
	    @Test
	    public void testPostPlaylistBadRequestException() {
	        var expected = new BadRequestException();
	        when(playlistServiceMock.addPlaylist(playlistDTO, userId)).thenThrow(expected);

	        assertThrows(BadRequestException.class, () -> sut.postPlaylist(playlistDTO, userId));
	    }
	    
	    @Test
	    public void testPostPlaylistServerErrorException() {
	        var expected = new ServerErrorException();
	        when(playlistServiceMock.addPlaylist(playlistDTO, userId)).thenThrow(expected);

	        assertThrows(ServerErrorException.class, () -> sut.postPlaylist(playlistDTO, userId));
	    }

	    @Test
	    public void testPostPlaylistCalls() {
	        sut.postPlaylist(playlistDTO, userId);

	        verify(playlistServiceMock, times(1)).addPlaylist(playlistDTO, userId);
	    }
    	    
    }
   
    /*--- PLAYLIST - DeletePlaylist ---*/
    
    @Nested
    @DisplayName("`DeletePlaylist` tests")
    class DeletePlaylistTests {
    	
    	@Test    
        public void testDeletePlaylistResponseOk() {
            var expected = Response.Status.OK;
            var result = sut.deletePlaylist(playlistId, userId);

            assertEquals(expected.getStatusCode(), result.getStatus());
        }
        
        @Test
        public void testDeletePlaylistBadRequestException() {
            var expected = new BadRequestException();
            when(playlistServiceMock.deletePlaylist(playlistId, userId)).thenThrow(expected);

            assertThrows(BadRequestException.class, () -> sut.deletePlaylist(playlistId, userId));
        }
        
        @Test
        public void testDeletePlaylistServerErrorException() {
            var expected = new ServerErrorException();
            when(playlistServiceMock.deletePlaylist(playlistId, userId)).thenThrow(expected);

            assertThrows(ServerErrorException.class, () -> sut.deletePlaylist(playlistId, userId));
        }
        
        @Test
        public void testDeletePlaylistCallsDeletePlaylist() {
            sut.deletePlaylist(playlistId, userId);

            verify(playlistServiceMock, times(1)).deletePlaylist(playlistId, userId);
        }
    }
    
    /*--- PLAYLIST - PutPlaylist ---*/
    
    @Nested
    @DisplayName("`PutPlaylist` tests")
    class PutPlaylistTests {
    	
    	@Test
        public void testPutPlaylistResponseOk() {
            var expected = Response.Status.OK;
            var result = sut.putPlaylist(playlistId, userId, playlistDTO);

            assertEquals(expected.getStatusCode(), result.getStatus());
        }
        
        @Test
        public void testPutPlaylistBadRequestException() {
            var expected = new BadRequestException();
            when(playlistServiceMock.putPlaylist(playlistDTO, playlistId, userId)).thenThrow(expected);

            assertThrows(BadRequestException.class, () -> sut.putPlaylist(playlistId, userId, playlistDTO));
        }
        
        @Test
        public void testPutPlaylistServerErrorException() {
            var expected = new ServerErrorException();
            when(playlistServiceMock.putPlaylist(playlistDTO, playlistId, userId)).thenThrow(expected);

            assertThrows(ServerErrorException.class, () -> sut.putPlaylist(playlistId, userId, playlistDTO));
        }
        
        @Test
        public void testPutPlaylistCalls() {
            sut.putPlaylist(playlistId, userId, playlistDTO);

            verify(playlistServiceMock, times(1)).putPlaylist(playlistDTO, playlistId, userId);
        }
    }
    
    
    /*--- PLAYLIST TRACKS - GetTracksFromPlaylist ---*/
    
    @Nested
    @DisplayName("`GetTracksFromPlaylist` tests")
    class GetTracksFromPlaylistTests {
    	
    	@Test
        public void testGetTracksFromPlaylistResponseOk() {
            var expected = Response.Status.OK;
            var result = sut.getTracksFromPlaylist(playlistId, userId);

            assertEquals(expected.getStatusCode(), result.getStatus());
        }
        
        @Test
        public void testGetTracksFromPlaylistBadRequestException() {
            var expected = new BadRequestException();
            when(playlistServiceMock.getTracksFromPlaylist(playlistId)).thenThrow(expected);

            assertThrows(BadRequestException.class, () -> sut.getTracksFromPlaylist(playlistId, userId));
        }
        
        @Test
        public void testGetTracksFromPlaylistServerErrorException() {
            var expected = new ServerErrorException();
            when(playlistServiceMock.getTracksFromPlaylist(playlistId)).thenThrow(expected);

            assertThrows(ServerErrorException.class, () -> sut.getTracksFromPlaylist(playlistId, userId));
        }
        
        @Test
        public void testGetTracksFromPlaylistCalls() {
            sut.getTracksFromPlaylist(playlistId, userId);

            verify(playlistServiceMock, times(1)).getTracksFromPlaylist(playlistId);
        }
    }
    
    
    /*--- PLAYLIST TRACKS - AddTrackToPlaylist ---*/
    
    @Nested
    @DisplayName("`AddTrackToPlaylist` tests")
    class AddTrackToPlaylistTests {
    	
    	@Test
        public void testAddTrackToPlaylistResponseOk() {
            var expected = Response.Status.OK;
            var result = sut.addTrackToPlaylist(playlistId, userId, trackDto);

            assertEquals(expected.getStatusCode(), result.getStatus());
        }
        
        @Test
        public void testAddTrackToPlaylistBadRequestException() {
            var expected = new BadRequestException();
            when(playlistServiceMock.addTrackToPlaylist(userId, playlistId, trackDto)).thenThrow(expected);

            assertThrows(BadRequestException.class, () -> sut.addTrackToPlaylist(playlistId, userId, trackDto));
        }
        
        @Test
        public void testAddTrackToPlaylistServerErrorException() {
            var expected = new ServerErrorException();
            when(playlistServiceMock.addTrackToPlaylist(userId, playlistId, trackDto)).thenThrow(expected);

            assertThrows(ServerErrorException.class, () -> sut.addTrackToPlaylist(playlistId, userId, trackDto));
        }
        
        @Test
        public void testAddTrackToPlaylistCalls() {
        	sut.addTrackToPlaylist(playlistId, userId, trackDto);

            verify(playlistServiceMock, times(1)).addTrackToPlaylist(userId, playlistId, trackDto);
        }
    }
    
    
    /*--- PLAYLIST TRACKS - DeleteTrackFromPlaylist ---*/
    
    @Nested
    @DisplayName("`AddTrackToPlaylist` tests")
    class DeleteTrackFromPlaylistTests {
    	
    	@Test
	    public void testDeleteTrackFromPlaylistResponseOk() {
	        var expected = Response.Status.OK;
	        var result = sut.deleteTrackFromPlaylist(playlistId, trackId, userId);

	        assertEquals(expected.getStatusCode(), result.getStatus());
	    }
	    
	    @Test
	    public void testDeleteTrackFromPlaylistBadRequestException() {
	        var expected = new BadRequestException();
	        when(playlistServiceMock.deleteTrackFromPlaylist(playlistId, trackId)).thenThrow(expected);

	        assertThrows(BadRequestException.class, () -> sut.deleteTrackFromPlaylist(playlistId, trackId, userId));
	    }
	    
	    @Test
	    public void testDeleteTrackFromPlaylistServerErrorException() {
	        var expected = new ServerErrorException();
	        when(playlistServiceMock.deleteTrackFromPlaylist(playlistId, trackId)).thenThrow(expected);

	        assertThrows(ServerErrorException.class, () -> sut.deleteTrackFromPlaylist(playlistId, trackId, userId));
	    }
	    
	    @Test
	    public void testDeleteTrackFromPlaylistCalls() {
	    	sut.deleteTrackFromPlaylist(playlistId, trackId, userId);

	        verify(playlistServiceMock, times(1)).deleteTrackFromPlaylist(playlistId, trackId);
	    }
    }
 
}



















