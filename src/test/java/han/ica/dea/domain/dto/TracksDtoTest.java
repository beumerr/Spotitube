package han.ica.dea.domain.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TracksDtoTest {

    private TracksDto sut;
    
    private ArrayList<TrackDto> tracks;
    
    @BeforeEach
    public void setup() {
    	sut = new TracksDto(tracks);
    }
    
    @Nested
    @DisplayName("`TracksDto` Tests")
    class ValidateUserTests {
    	
    	@Test
        public void testGetTracks() {
    		var expected = tracks;
        	var actual = sut.getTracks();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetTracks() {
        	tracks = new ArrayList<TrackDto>();
            var expected = tracks;

            sut.setTracks(tracks);
            var actual = sut.getTracks();

            assertEquals(expected, actual);
        }
        
    }
}



















