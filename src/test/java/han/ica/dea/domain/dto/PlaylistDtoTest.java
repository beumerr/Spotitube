package han.ica.dea.domain.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class PlaylistDtoTest {

    private PlaylistDto sut;
       
    private int id = 1;
    private int newId = 2;
    
    private String name = "name";
    private String newName = "newName";
    
    private boolean owner = true;
    private boolean newOwner = false;
    
    private ArrayList<TrackDto> tracks;
    
    @BeforeEach
    public void setup() {
    	sut = new PlaylistDto(id, name, owner);
    }
    
    @Nested
    @DisplayName("`PlaylistDto.id` Tests")
    class PlaylistDtoIdTests {
    	
    	@Test
        public void testGetId() {
    		var expected = id;
        	var result = sut.getId();
        	
        	assertEquals(expected, result);
        }

        @Test
        public void testSetId() {
            var expected = newId;
            sut.setId(newId);
            var result = sut.getId();

            assertEquals(expected, result);
        }
    }
    
    @Nested
    @DisplayName("`PlaylistDto.name` Tests")
    class PlaylistDtoNameTests {
    	
    	@Test
        public void testGetName() {
    		var expected = name;
        	var result = sut.getName();
        	
        	assertEquals(expected, result);
        }

        @Test
        public void testSetName() {
            var expected = newName;
            sut.setName(newName);
            var result = sut.getName();

            assertEquals(expected, result);
        }
    }
    
    @Nested
    @DisplayName("`PlaylistDto.owner` Tests")
    class PlaylistDtoOwnerTests {
    	
    	@Test
        public void testIsOwner() {
    		var expected = owner;
        	var result = sut.isOwner();
        	
        	assertEquals(expected, result);
        }

        @Test
        public void testSetOwner() {
            var expected = newOwner;
            sut.setOwner(newOwner);
            var result = sut.isOwner();

            assertEquals(expected, result);
        }
    }
    
    @Nested
    @DisplayName("`PlaylistDto.tracks` Tests")
    class PlaylistDtoTracksTests {
    	
    	@Test
        public void testGetTracks() {
    		var expected = tracks;
        	var result = sut.getTracks();
        	
        	assertEquals(expected, result);
        }

        @Test
        public void testSetTracks() {
            var expected = tracks;
            sut.setTracks(tracks);
            var result = sut.getTracks();

            assertEquals(expected, result);
        }
    }

}



















