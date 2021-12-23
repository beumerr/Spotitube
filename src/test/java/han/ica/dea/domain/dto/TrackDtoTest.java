package han.ica.dea.domain.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrackDtoTest {

    private TrackDto sut;
    
    private int id = 1;
    private int newId = 2;
    private int duration = 1;
    private int newDuration = 2;
    private int playcount = 1;
    private int newPlaycount = 2;
    
    private boolean offlineAvailable = true;
    private boolean newOfflineAvailable = false;
    
    private String title = "title";
    private String newTitle = "newTitle";
    private String performer = "performer";
    private String newPerformer = "newPerformer";
    private String album = "album";
    private String newAlbum = "newAlbum";
    private String publicationDate = "publicationDate";
    private String newPublicationDate = "newPublicationDate";
    private String description = "description";
    private String newDescription = "newDescription";
    
    @BeforeEach
    public void setup() {
    	sut = new TrackDto(
			id,
			title,
			performer,
			duration,
			album,
			playcount,
			publicationDate,
			description,
			offlineAvailable
		);
    }
    
    @Nested
    @DisplayName("`TrackDto.id` Tests")
    class TrackDtoIdTests {
    	
    	@Test
        public void testGetId() {
    		var expected = id;
        	var actual = sut.getId();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetId() {
            var expected = newId;
            sut.setId(expected);
            var actual = sut.getId();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`TrackDto.title` Tests")
    class TrackDtoTitleTests {
    	
    	@Test
        public void testGetTitle() {
    		var expected = title;
        	var actual = sut.getTitle();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetTitle() {
            var expected = newTitle;
            sut.setTitle(expected);
            var actual = sut.getTitle();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`TrackDto.performer` Tests")
    class TrackDtoPerformerTests {
    	
    	@Test
        public void testGetPerformer() {
    		var expected = performer;
        	var actual = sut.getPerformer();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetPerformer() {
            var expected = newPerformer;
            sut.setPerformer(expected);
            var actual = sut.getPerformer();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`TrackDto.duration` Tests")
    class TrackDtoDurationTests {
    	
    	@Test
        public void testGetDuration() {
    		var expected = duration;
        	var actual = sut.getDuration();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetDuration() {
            var expected = newDuration;
            sut.setDuration(expected);
            var actual = sut.getDuration();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`TrackDto.album` Tests")
    class TrackDtoAlbumTests {
    	
    	@Test
        public void testGetAlbum() {
    		var expected = album;
        	var actual = sut.getAlbum();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetAlbum() {
            var expected = newAlbum;
            sut.setAlbum(expected);
            var actual = sut.getAlbum();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`TrackDto.playcount` Tests")
    class TrackDtoPlaycountTests {
    	
    	@Test
        public void testGetPlaycount() {
    		var expected = playcount;
        	var actual = sut.getPlaycount();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetPlaycount() {
            var expected = newPlaycount;
            sut.setPlaycount(newPlaycount);
            var actual = sut.getPlaycount();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`TrackDto.publicationDate` Tests")
    class TrackDtoPublicationDateTests {
    	
    	@Test
        public void testGetPublicationDate() {
    		var expected = publicationDate;
        	var actual = sut.getPublicationDate();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetPublicationDate() {
            var expected = newPublicationDate;
            sut.setPublicationDate(expected);
            var actual = sut.getPublicationDate();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`TrackDto.description` Tests")
    class TrackDtoDescriptionTests {
    	
    	@Test
        public void testGetDescription() {
    		var expected = description;
        	var actual = sut.getDescription();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetDescription() {
            var expected = newDescription;
            sut.setDescription(expected);
            var actual = sut.getDescription();

            assertEquals(expected, actual);
        }
    }
    
    @Nested
    @DisplayName("`TrackDto.offlineAvailable` Tests")
    class TrackDtoOfflineAvailableTests {
    	
    	@Test
        public void testGetOfflineAvailable() {
    		var expected = offlineAvailable;
        	var actual = sut.getOfflineAvailable();
        	
        	assertEquals(expected, actual);
        }

        @Test
        public void testSetOfflineAvailable() {
            var expected = newOfflineAvailable;
            sut.setOfflineAvailable(expected);
            var actual = sut.getOfflineAvailable();

            assertEquals(expected, actual);
        }
    }
}



















