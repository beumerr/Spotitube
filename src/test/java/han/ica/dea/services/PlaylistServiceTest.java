package han.ica.dea.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import han.ica.dea.persistence.dao.PlaylistDao;
import han.ica.dea.persistence.dao.TrackDao;
import han.ica.dea.domain.dto.PlaylistDto;
import han.ica.dea.domain.dto.PlaylistsDto;
import han.ica.dea.domain.dto.TrackDto;
import han.ica.dea.domain.dto.TracksDto;
import han.ica.dea.domain.exceptions.AuthenticateException;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.ServerErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlaylistServiceTest {

    private PlaylistService sut;
    
    private PlaylistDto playlistDto;
    
    private PlaylistsDto playlistsDto;
    
    private TracksDto tracksDto;
    
    private TrackDto trackDto;
    
    private PlaylistDao playlistDaoMock;
    
    private TrackDao trackDaoMock;
    
    private static int playlistId = 1;
    private static int userId = 1;
    private static int trackId = 1;

    @BeforeEach
    public void setup() {
        sut = new PlaylistService();
        playlistDaoMock = Mockito.mock(PlaylistDao.class);
        trackDaoMock = Mockito.mock(TrackDao.class);
        sut.setPlaylistDao(playlistDaoMock);
        sut.setTrackDao(trackDaoMock);
        trackDto = new TrackDto();
        tracksDto = new TracksDto();
        playlistDto = new PlaylistDto();
        playlistsDto = new PlaylistsDto();
    }
    
    /*--- PLAYLIST - GetAllPlaylists ---*/
    
    @Nested
    @DisplayName("`GetAllPlaylists` Tests")
    class GetAllPlaylistsTests {
    	
    	@Test
        public void testGetAllPlaylists() {
        	var expected = playlistsDto;
        	when(playlistDaoMock.getAllPlaylists(userId)).thenReturn(expected);
            var result = sut.getAllPlaylists(userId);
            
            assertEquals(expected, result);
        }
        
        @Test
        public void testGetAllPlaylistsBadRequestException() {
        	var expected = new AuthenticateException();
        	when(playlistDaoMock.getAllPlaylists(userId)).thenThrow(expected);

        	assertThrows(AuthenticateException.class, () -> sut.getAllPlaylists(userId));
        }
        
        @Test
        public void testGetAllPlaylistsServerErrorException() {
        	var expected = new ServerErrorException();
        	when(playlistDaoMock.getAllPlaylists(userId)).thenThrow(expected);

        	assertThrows(ServerErrorException.class, () -> sut.getAllPlaylists(userId));
        }
        
        @Test
        public void testGetAllPlaylistsCalls() {
        	sut.getAllPlaylists(userId);

            verify(playlistDaoMock, times(1)).getAllPlaylists(userId);
        }
    }
    
    /*--- PLAYLIST - AddPlaylist ---*/
    
    @Nested
    @DisplayName("`AddPlaylist` Tests")
    class AddPlaylistTests {
    	
    	@Test
        public void testAddPlaylist() {
        	var expected = playlistsDto;
        	when(playlistDaoMock.getAllPlaylists(userId)).thenReturn(expected);
            var result = sut.addPlaylist(playlistDto, userId);
            
            assertEquals(expected, result);
        }
        
        @Test
        public void testAddPlaylistBadRequestException() {
        	var expected = new BadRequestException();
        	doThrow(expected).when(playlistDaoMock).addPlaylist(userId, playlistDto);

        	assertThrows(BadRequestException.class, () -> sut.addPlaylist(playlistDto, userId));
        }
        
        @Test
        public void testAddPlaylistServerErrorException() {
        	var expected = new ServerErrorException();
        	doThrow(expected).when(playlistDaoMock).addPlaylist(userId, playlistDto);

        	assertThrows(ServerErrorException.class, () -> sut.addPlaylist(playlistDto, userId));
        }
        
        @Test
        public void testAddPlaylistCalls() {
        	sut.addPlaylist(playlistDto, userId);
        	
            verify(playlistDaoMock, times(1)).addPlaylist(userId, playlistDto);
            verify(playlistDaoMock, times(1)).getAllPlaylists(userId);
        }
    }
    
    /*--- PLAYLIST - DeletePlaylist ---*/
    
    @Nested
    @DisplayName("`DeletePlaylist` Tests")
    class DeletePlaylistTests {
    	
    	@Test
        public void testDeletePlaylist() {
        	var expected = playlistsDto;
        	when(playlistDaoMock.getAllPlaylists(userId)).thenReturn(expected);
            var result = sut.deletePlaylist(playlistId, userId);
            
            assertEquals(expected, result);
        }
        
        @Test
        public void testDeletePlaylistBadRequestException() {
        	var expected = new BadRequestException();
        	doThrow(expected).when(playlistDaoMock).deletePlaylist(playlistId);

        	assertThrows(BadRequestException.class, () -> sut.deletePlaylist(playlistId, userId));
        }
        
        @Test
        public void testDeletePlaylistServerErrorException() {
        	var expected = new ServerErrorException();
        	doThrow(expected).when(playlistDaoMock).deletePlaylist(playlistId);

        	assertThrows(ServerErrorException.class, () -> sut.deletePlaylist(playlistId, userId));
        }
        
        @Test
        public void testDeletePlaylistCalls() {
        	sut.deletePlaylist(playlistId, userId);
        	
            verify(playlistDaoMock, times(1)).deletePlaylist(playlistId);
            verify(playlistDaoMock, times(1)).getAllPlaylists(userId);
        }
    }
    
    /*--- PLAYLIST - PutPlaylist ---*/
    
    @Nested
    @DisplayName("`PutPlaylist` Tests")
    class PutPlaylistTests {
    	
    	@Test
        public void testPutPlaylist() {
        	var expected = playlistsDto;
        	when(playlistDaoMock.getAllPlaylists(userId)).thenReturn(expected);
            var result = sut.putPlaylist(playlistDto, playlistId, userId);
            
            assertEquals(expected, result);
        }
        
        @Test
        public void testPutPlaylistBadRequestException() {
        	var expected = new BadRequestException();
        	doThrow(expected).when(playlistDaoMock).putPlaylist(playlistId, playlistDto);

        	assertThrows(BadRequestException.class, () -> sut.putPlaylist(playlistDto, playlistId, userId));
        }
        
        @Test
        public void testPutPlaylistServerErrorException() {
        	var expected = new ServerErrorException();
        	doThrow(expected).when(playlistDaoMock).putPlaylist(playlistId, playlistDto);

        	assertThrows(ServerErrorException.class, () -> sut.putPlaylist(playlistDto, playlistId, userId));
        }
        
        @Test
        public void testPutPlaylistCalls() {
        	sut.putPlaylist(playlistDto, playlistId, userId);
        	
            verify(playlistDaoMock, times(1)).putPlaylist(playlistId, playlistDto);
            verify(playlistDaoMock, times(1)).getAllPlaylists(userId);
        }
    }
    
    /*--- PLAYLIST TRACKS - GetTracksFromPlaylist ---*/
    
    @Nested
    @DisplayName("`GetTracksFromPlaylist` Tests")
    class GetTracksFromPlaylistTests {
    	
    	@Test
        public void testGetTracksFromPlaylist() {
        	var expected = tracksDto;
        	when(trackDaoMock.getTracksFromPlaylist(playlistId)).thenReturn(expected);
            var result = sut.getTracksFromPlaylist(playlistId);
            
            assertEquals(expected, result);
        }
        
        @Test
        public void testGetTracksFromPlaylistBadRequestException() {
        	var expected = new BadRequestException();
        	doThrow(expected).when(trackDaoMock).getTracksFromPlaylist(playlistId);

        	assertThrows(BadRequestException.class, () -> sut.getTracksFromPlaylist(playlistId));
        }
        
        @Test
        public void testGetTracksFromPlaylistServerErrorException() {
        	var expected = new ServerErrorException();
        	doThrow(expected).when(trackDaoMock).getTracksFromPlaylist(playlistId);

        	assertThrows(ServerErrorException.class, () -> sut.getTracksFromPlaylist(playlistId));
        }
        
        @Test
        public void testGetTracksFromPlaylistCalls() {
    		sut.getTracksFromPlaylist(playlistId);
      
            verify(trackDaoMock, times(1)).getTracksFromPlaylist(playlistId);
        }
    }
    

    /*--- PLAYLIST TRACKS - AddTrackToPlaylist ---*/
    
    @Nested
    @DisplayName("`AddTrackToPlaylist` Tests")
    class AddTrackToPlaylistTests {
    	
    	@Test
        public void testAddTrackToPlaylist() {
        	var expected = tracksDto;
        	when(trackDaoMock.getTracksFromPlaylist(playlistId)).thenReturn(expected);
            var result = sut.addTrackToPlaylist(userId, playlistId, trackDto);
            
            assertEquals(expected, result);
        }
        
        @Test
        public void testAddTrackToPlaylistBadRequestException() {
        	var expected = new BadRequestException();
        	doThrow(expected).when(trackDaoMock).addTrackToPlaylist(playlistId, trackDto);

        	assertThrows(BadRequestException.class, () -> sut.addTrackToPlaylist(userId, playlistId, trackDto));
        }
        
        @Test
        public void testAddTrackToPlaylistServerErrorException() {
        	var expected = new ServerErrorException();
        	doThrow(expected).when(trackDaoMock).addTrackToPlaylist(playlistId, trackDto);

        	assertThrows(ServerErrorException.class, () -> sut.addTrackToPlaylist(userId, playlistId, trackDto));
        }
        
        @Test
        public void testAddTrackToPlaylistCalls() {
        	sut.addTrackToPlaylist(userId, playlistId, trackDto);
        	
        	verify(trackDaoMock, times(1)).addTrackToPlaylist(playlistId, trackDto);
            verify(trackDaoMock, times(1)).getTracksFromPlaylist(playlistId);
        }

    }
    
    /*--- PLAYLIST TRACKS - DeleteTrackFromPlaylist ---*/
    
    @Nested
    @DisplayName("`DeleteTrackFromPlaylist` Tests")
    class DeleteTrackFromPlaylistTests {
    	
    	@Test
	    public void testDeleteTrackFromPlaylist() {
	    	var expected = tracksDto;
	    	when(trackDaoMock.getTracksFromPlaylist(playlistId)).thenReturn(expected);
	        var result = sut.deleteTrackFromPlaylist(playlistId, trackId);
	        
	        assertEquals(expected, result);
	    }
	    
	    @Test
	    public void testDeleteTrackFromPlaylistBadRequestException() {
	    	var expected = new BadRequestException();
	    	doThrow(expected).when(trackDaoMock).deleteTrackFromPlaylist(playlistId, trackId);

	    	assertThrows(BadRequestException.class, () -> sut.deleteTrackFromPlaylist(playlistId, trackId));
	    }
	    
	    @Test
	    public void testDeleteTrackFromPlaylistServerErrorException() {
	    	var expected = new ServerErrorException();
	    	doThrow(expected).when(trackDaoMock).deleteTrackFromPlaylist(playlistId, trackId);

	    	assertThrows(ServerErrorException.class, () -> sut.deleteTrackFromPlaylist(playlistId, trackId));
	    }
	    
	    @Test
	    public void testDeleteTrackFromPlaylistCalls() {
	    	sut.deleteTrackFromPlaylist(playlistId, trackId);
	    	
	    	verify(trackDaoMock, times(1)).deleteTrackFromPlaylist(playlistId, trackId);
	        verify(trackDaoMock, times(1)).getTracksFromPlaylist(playlistId);
	    }
    }
	
}



















