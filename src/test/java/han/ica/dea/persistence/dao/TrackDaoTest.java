package han.ica.dea.persistence.dao;

import han.ica.dea.domain.dto.TrackDto;
import han.ica.dea.domain.dto.TracksDto;
import han.ica.dea.domain.exceptions.BadRequestException;
import han.ica.dea.domain.exceptions.DbConnectionException;
import han.ica.dea.domain.exceptions.ServerErrorException;
import han.ica.dea.persistence.context.DbConnection;
import han.ica.dea.persistence.mappings.data.TracksDataMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;
import java.util.ArrayList;

public class TrackDaoTest {
	
	private TrackDao sut;
	
    private TracksDataMapper tracksDataMapper;
    
    private DbConnection dbConnectionMock;
    
    private Connection connectionMock;
    
    private PreparedStatement preparedStatementMock;
    
    private ResultSet resultSetMock;
    
    private TracksDto tracksDto;

    private static final int ID = 1;  

    @BeforeEach
    public void setup() {
        sut = new TrackDao();
        tracksDataMapper = mock(TracksDataMapper.class);
        dbConnectionMock = mock(DbConnection.class);
        resultSetMock = mock(ResultSet.class);
        connectionMock = mock(Connection.class);
        preparedStatementMock = mock(PreparedStatement.class);
        sut.setMapper(tracksDataMapper);
        sut.setConnection(dbConnectionMock);
        tracksDto = new TracksDto(new ArrayList<TrackDto>());
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
            var expected = tracksDataMapper;
            sut.setMapper(tracksDataMapper);
            var result = sut.getMapper();

            assertEquals(expected, result);
        }
    }

    @Nested
    @DisplayName("`getTracksFromPlaylist` tests")
    public class GetTracksFromPlaylistTests {
    	
    	@Test
        void testGetTracksFromPlaylistCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(tracksDataMapper.mapToDTO(resultSetMock)).thenReturn(tracksDto);
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
                sut.getTracksFromPlaylist(ID);

                verify(dbConnectionMock, times(2)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(tracksDataMapper, times(1)).mapToDTO(resultSetMock);
                verify(preparedStatementMock, times(1)).executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    	
    	@Test
        void testGetTracksFromPlaylistReturn() {
            try {
            	var expected = tracksDto;
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(tracksDataMapper.mapToDTO(resultSetMock)).thenReturn(tracksDto);
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
                var result = sut.getTracksFromPlaylist(ID);
                
                assertEquals(expected, result);
            } catch (Exception e) {}
        }

        @Test
        void testGetTracksFromPlaylistSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new SQLException()).when(preparedStatementMock).executeQuery();
            	
                assertThrows(BadRequestException.class, () -> sut.getTracksFromPlaylist(ID));
            } 
            catch (Exception e) {}
        }

        @Test
        void testGetTracksFromPlaylistxception() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new Exception()).when(preparedStatementMock).executeQuery();

                assertThrows(ServerErrorException.class, () -> sut.getTracksFromPlaylist(ID));
            } 
            catch (Exception e) {}
        }
    }
    
    @Nested
    @DisplayName("`getTracksNotInPlaylist` tests")
    public class GetTracksNotInPlaylistTest {
    	
    	@Test
        void testGetTracksNotInPlaylistCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(tracksDataMapper.mapToDTO(resultSetMock)).thenReturn(tracksDto);
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
                sut.getTracksNotInPlaylist(ID);

                verify(dbConnectionMock, times(2)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(preparedStatementMock, times(1)).executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    	
    	@Test
        void testGetTracksNotInPlaylistReturn() {
            try {
            	var expected = tracksDto;
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(tracksDataMapper.mapToDTO(resultSetMock)).thenReturn(tracksDto);
                when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
            	var result = sut.getTracksNotInPlaylist(ID);
                
                assertEquals(expected, result);
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }
    	

        @Test
        void testGetTracksNotInPlaylistSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new SQLException()).when(preparedStatementMock).executeQuery();
            	
                assertThrows(BadRequestException.class, () -> sut.getTracksNotInPlaylist(ID));
            } 
            catch (Exception e) {}
        }

        @Test
        void testGetTracksNotInPlaylistException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
            	doThrow(new Exception()).when(preparedStatementMock).executeQuery();

                assertThrows(ServerErrorException.class, () -> sut.getTracksNotInPlaylist(ID));
            } 
            catch (Exception e) {}
        }
    }
    
    @Nested
    @DisplayName("`addTrackToPlaylist` tests")
    public class AddTrackToPlaylistTests {
    	
    	@Test
        void testAddTrackToPlaylistCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                
                sut.addTrackToPlaylist(ID, new TrackDto());

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
        void testAddTrackToPlaylistSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeUpdate()).thenThrow(new SQLException());

    			assertThrows(BadRequestException.class, () -> sut.addTrackToPlaylist(ID, new TrackDto()));
            } 
            catch (SQLException e) {}
        }
        
        @Test
        void testAddTrackToPlaylistDbConnectionException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(null);
            	when(dbConnectionMock.get()).thenThrow(new SQLException());

    			assertThrows(DbConnectionException.class, () -> sut.addTrackToPlaylist(ID, new TrackDto()));
            } 
            catch (Exception e) {}
        }

        @Test
        void testAddTrackToPlaylistException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeUpdate()).thenThrow(new Exception());

    			assertThrows(ServerErrorException.class, () -> sut.addTrackToPlaylist(ID, new TrackDto()));
            } 
            catch (Exception e) {}
        }
    }
    
    @Nested
    @DisplayName("`deleteTrackFromPlaylist` tests")
    public class DeleteTrackFromPlaylistTests {
    	
    	@Test
        void testDeleteTrackFromPlaylistCalls() {
            try {
                doNothing().when(dbConnectionMock).close();
                when(dbConnectionMock.get()).thenReturn(connectionMock);
                when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(resultSetMock.next()).thenReturn(true, false);
                
                sut.deleteTrackFromPlaylist(ID, ID);

                verify(dbConnectionMock, times(2)).get();
                verify(dbConnectionMock, times(1)).close();
                verify(preparedStatementMock, times(1)).executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }
        }

        @Test
        void testDeleteTrackFromPlaylistSQLException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeUpdate()).thenThrow(new SQLException());

    			assertThrows(BadRequestException.class, () -> sut.deleteTrackFromPlaylist(ID, ID));
            } 
            catch (Exception e) {}
        }

        @Test
        void testDeleteTrackFromPlaylistException() {
            try {
            	when(dbConnectionMock.get()).thenReturn(connectionMock);
            	when(dbConnectionMock.get().prepareStatement(any(String.class))).thenReturn(preparedStatementMock);
                when(preparedStatementMock.executeUpdate()).thenThrow(new Exception());

    			assertThrows(ServerErrorException.class, () -> sut.deleteTrackFromPlaylist(ID, ID));
            } 
            catch (Exception e) {}
        }
    }
}
