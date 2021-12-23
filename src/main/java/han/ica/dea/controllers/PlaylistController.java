package han.ica.dea.controllers;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import han.ica.dea.domain.dto.PlaylistDto;
import han.ica.dea.domain.dto.TrackDto;
import han.ica.dea.services.PlaylistService;

@Singleton
@Path("playlists")
public class PlaylistController {
	
    private PlaylistService playlistService;
    
    /*--- PLAYLIST ---*/
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylists(@QueryParam("userId") int userId) {
    	return Response
                .status(Response.Status.OK)
                .entity(playlistService.getAllPlaylists(userId))
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postPlaylist(PlaylistDto playlist, @QueryParam("userId") int userId) {
    	return Response
                .status(Response.Status.OK)
                .entity(playlistService.addPlaylist(playlist, userId))
                .build();
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("userId") int userId) {
    	return Response
                .status(Response.Status.OK)
                .entity(playlistService.deletePlaylist(id, userId))
                .build();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPlaylist(
    		@PathParam("id") int id, 
    		@QueryParam("userId") int userId,
    		PlaylistDto playlist
    		) {
    	return Response
                .status(Response.Status.OK)
                .entity(playlistService.putPlaylist(playlist, id, userId))
                .build();
    }
    
    /*--- PLAYLIST TRACKS ---*/
    
    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(
    		@PathParam("id") int id, 
    		@QueryParam("userId") int userId
    		) {
    	return Response
                .status(Response.Status.OK)
                .entity(playlistService.getTracksFromPlaylist(id, userId))
                .build();
    }
    
    @POST
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(
    		@PathParam("id") int playlistId,
    		@QueryParam("userId") int userId, 
    		TrackDto track
    		) {
    	return Response
                .status(Response.Status.OK)
                .entity(playlistService.addTrackToPlaylist(userId, playlistId, track))
                .build();
    }
    
    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(
    		@PathParam("playlistId") int playlistId, 
    		@PathParam("trackId") int trackId,
    		@QueryParam("userId") int userId
    		) {
    	return Response
                .status(Response.Status.OK)
                .entity(playlistService.deleteTrackFromPlaylist(playlistId, trackId))
                .build();
    }
    
    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
    	this.playlistService = playlistService;
    }

    public PlaylistService getPlaylistService() {
    	return playlistService;
    }
}
