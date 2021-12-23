package han.ica.dea.domain.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class PlaylistsDtoTest {

    private PlaylistsDto sut;
    
    private ArrayList<PlaylistDto> playlists;
    
    private int length = 1;
    private int newLength = 2;
    
    @BeforeEach
    public void setup() {
    	sut = new PlaylistsDto(playlists, length);
    }
    
    @Nested
    @DisplayName("`PlaylistsDto.playlists` Tests")
    class PlaylistPlaylistsTests {
    	
    	@Test
        public void testGetTracks() {
    		var expected = playlists;
        	var actual = sut.getPlaylists();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetTracks() {
        	playlists = new ArrayList<PlaylistDto>();
            var expected = playlists;

            sut.setPlaylists(playlists);
            var actual = sut.getPlaylists();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`PlaylistsDto.length` Tests")
    class ValidateUserTests {
    	
    	@Test
        public void testGetTracks() {
    		var expected = length;
        	var actual = sut.getLength();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetTracks() {
            var expected = newLength;
            sut.setLength(newLength);
            var actual = sut.getLength();

            assertEquals(expected, actual);
        }
    }
}



















