package han.ica.dea.persistence.dao;

import han.ica.dea.domain.dto.PlaylistDto;
import han.ica.dea.domain.dto.PlaylistsDto;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.ServerErrorException;
import han.ica.dea.persistence.context.DbConnection;
import han.ica.dea.persistence.mappings.data.PlaylistsDataMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.util.ArrayList;

public class PlaylistDaoTest {
	
	private PlaylistDao sut;
	
    private PlaylistsDataMapper playlistsDataMapper;
    
    private DbConnection dbConnectionMock;
    
    private Connection connectionMock;
    
    private PreparedStatement preparedStatementMock;
    
    private ResultSet resultSetMock;
    
    private ArrayList<PlaylistDto> playlists = new ArrayList<PlaylistDto>();
    
    private PlaylistDto playlistDto;

    private static final int ID = 1;
    
    private static final String NAME = "name";
    
    private static final boolean  OWNER = true;

    @BeforeEach
    public void setup() {
        sut = new PlaylistDao();
        playlistsDataMapper = mock(PlaylistsDataMapper.class);
        dbConnectionMock = mock(DbConnection.class);
        resultSetMock = mock(ResultSet.class);
        connectionMock = mock(Connection.class);
        preparedStatementMock = mock(PreparedStatement.class);
        sut.setMapper(playlistsDataMapper);
        sut.setConnection(dbConnectionMock);
        playlistDto = new PlaylistDto(ID, NAME, OWNER);
        playlists.add(playlistDto);
    }
    
    @Nested
    @DisplayName("Injection tests")
    public class InjectionTests {
    	
    	@Test
        void testSetConnectionInjection() {
            var expected = dbConnectionMock;
            sut.setConnection(dbConnectionMock);
            var result = sut.getConnection();

            assertEquals(expected, result);
        }

        @Test
        void testSetMapperInjection() {
            var expected = playlistsDataMapper;
            sut.setMapper(playlistsDataMapper);
            var result = sut.getMapper();

            assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("`getAllPlaylists` tests")
    public class GetAllPlaylistsTests {
    	
    	@Test
        void testGetAllPlaylistsCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(playlistsDataMapper.mapToDTO(resultSetMock)).thenReturn(new ArrayList<PlaylistDto>());
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
                when(resultSetMock.next()).thenReturn(true, false);
                sut.getAllPlaylists(ID);

                verify(dbConnectionMock, times(3)).get();
                verify(dbConnectionMock, times(2)).close();
                verify(playlistsDataMapper, times(1)).mapToDTO(resultSetMock);
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    	
    	@Test
        void testGetAllPlaylistsReturn() {
            try {
            	var playlist = new ArrayList<PlaylistDto>();
            	var expected = new PlaylistsDto(playlist, 0);
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(playlistsDataMapper.mapToDTO(resultSetMock)).thenReturn(playlist);
                var result = sut.getAllPlaylists(ID);
                
                assertEquals(expected, result);
            } catch (Exception e) {}
        }

        @Test
        void testGetAllPlaylistsSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new SQLException()).when(preparedStatementMock).executeQuery();
            	
                assertThrows(BadRequestException.class, () -> sut.getAllPlaylists(ID));
            } 
            catch (Exception e) {}
        }

        @Test
        void testGetAllPlaylistsException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new Exception()).when(preparedStatementMock).executeQuery();

                assertThrows(ServerErrorException.class, () -> sut.getAllPlaylists(ID));
            } 
            catch (Exception e) {}
        }
    }
    
    @Nested
    @DisplayName("`getTotalDuration` tests")
    public class GetTotalDurationTests {
    	
    	@Test
        void testGetTotalDurationCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(playlistsDataMapper.mapToDTO(resultSetMock)).thenReturn(new ArrayList<PlaylistDto>());
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
                when(resultSetMock.next()).thenReturn(true, false);
                sut.getTotalDuration(ID);

                verify(dbConnectionMock, times(2)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(preparedStatementMock, times(1)).executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    	
    	@Test
        void testGetTotalDurationReturn() {
            try {
            	var expected = 0;
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(playlistsDataMapper.mapToDTO(resultSetMock)).thenReturn(new ArrayList<PlaylistDto>());
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
                when(resultSetMock.next()).thenReturn(true, false);
            	var result = sut.getTotalDuration(ID);
                
                assertEquals(expected, result);
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    	

        @Test
        void testGetTotalDurationSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new SQLException()).when(preparedStatementMock).executeQuery();
            	
                assertThrows(BadRequestException.class, () -> sut.getTotalDuration(ID));
            } 
            catch (Exception e) {}
        }

        @Test
        void testGetTotalDurationException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new Exception()).when(preparedStatementMock).executeQuery();

                assertThrows(ServerErrorException.class, () -> sut.getTotalDuration(ID));
            } 
            catch (Exception e) {}
        }
    }
    
    @Nested
    @DisplayName("`addPlaylist` tests")
    public class AddPlaylistTests {
    	
    	@Test
        void testAddPlaylistCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(playlistsDataMapper.mapToDTO(resultSetMock)).thenReturn(new ArrayList<PlaylistDto>());
                when(resultSetMock.next()).thenReturn(true, false);
                
                sut.addPlaylist(ID, playlistDto);

                verify(dbConnectionMock, times(5)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(dbConnectionMock.get(), times(1)).setAutoCommit(false);
                verify(preparedStatementMock, times(2)).executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }

        @Test
        void testAddPlaylistSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeUpdate()).thenThrow(new SQLException());

    			assertThrows(BadRequestException.class, () -> sut.addPlaylist(ID, playlistDto));
            } 
            catch (SQLException e) {}
        }

        @Test
        void testAddPlaylistException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeUpdate()).thenThrow(new Exception());

    			assertThrows(ServerErrorException.class, () -> sut.addPlaylist(ID, playlistDto));
            } 
            catch (Exception e) {}
        }
    }
    
    @Nested
    @DisplayName("`deletePlaylist` tests")
    public class DeletePlaylistTests {
    	
    	@Test
        void testDeletePlaylistCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(playlistsDataMapper.mapToDTO(resultSetMock)).thenReturn(new ArrayList<PlaylistDto>());
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
                when(resultSetMock.next()).thenReturn(true, false);
                
                sut.deletePlaylist(ID);

                verify(dbConnectionMock, times(2)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(preparedStatementMock, times(1)).executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }

        @Test
        void testDeletePlaylistSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeUpdate()).thenThrow(new SQLException());

    			assertThrows(BadRequestException.class, () -> sut.deletePlaylist(ID));
            } 
            catch (Exception e) {}
        }

        @Test
        void testDeletePlaylistException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeUpdate()).thenThrow(new Exception());

    			assertThrows(ServerErrorException.class, () -> sut.deletePlaylist(ID));
            } 
            catch (Exception e) {}
        }
    }
    
    @Nested
    @DisplayName("`putPlaylist` tests")
    public class PutPlaylistTest {
    	
    	@Test
        void testPutPlaylistCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(playlistsDataMapper.mapToDTO(resultSetMock)).thenReturn(new ArrayList<PlaylistDto>());
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
                when(resultSetMock.next()).thenReturn(true, false);
                
                sut.putPlaylist(ID, playlistDto);

                verify(dbConnectionMock, times(2)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(preparedStatementMock, times(1)).executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }

        @Test
        void testPutPlaylistSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeUpdate()).thenThrow(new SQLException());

    			assertThrows(BadRequestException.class, () -> sut.putPlaylist(ID, playlistDto));
            } 
            catch (Exception e) {}
        }

        @Test
        void testPutPlaylistException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get()).thenThrow(new Exception());
        

                assertThrows(ServerErrorException.class, () -> sut.putPlaylist(ID, playlistDto));
            } 
            catch (Exception e) {}
        }
    }
    
}
