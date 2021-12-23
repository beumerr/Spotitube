package han.ica.dea.persistence.mappings.data;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import han.ica.dea.domain.dto.PlaylistDto;

public class PlaylistsDataMapperTest {
	
	private PlaylistsDataMapper sut;
	
    private ResultSet resultSetMock;

    private int id = 1;
    
    private String name = "name";
    
    private boolean isowner = true;

    @BeforeEach
    public void setup() {
        sut = new PlaylistsDataMapper();
        resultSetMock = mock(ResultSet.class);
    }

    @Nested
    public class PlaylistDataMapperTests {
    	
        @Test
        public void testMapToDtoReturn() {
            try {
                var expected = new PlaylistDto(id, name, isowner);
                when(resultSetMock.next()).thenReturn(true, false);
                when(resultSetMock.getInt("id")).thenReturn(id);
                when(resultSetMock.getString("name")).thenReturn(name);
                when(resultSetMock.getBoolean("isowner")).thenReturn(isowner);

                var actual = sut.mapToDTO(resultSetMock);

                assertEquals(expected.getId(), actual.get(0).getId());
                assertEquals(expected.getName(), actual.get(0).getName());
                assertEquals(expected.isOwner(), actual.get(0).isOwner());
            } catch(SQLException e) {
                fail();
            }
        }

        @Test
        public void testMapToDtoIteration() {
            try {
                var expected = 2;
                when(resultSetMock.next()).thenReturn(true, true, false);
                when(resultSetMock.getInt("id")).thenReturn(id);
                when(resultSetMock.getString("name")).thenReturn(name);
                when(resultSetMock.getBoolean("isowner")).thenReturn(isowner);

                var value = sut.mapToDTO(resultSetMock);
                var actual = value.size();

                assertEquals(expected, actual);
            } catch(SQLException e) {
                fail();
            }
        }
    }
}
