package han.ica.dea.controllers;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import han.ica.dea.services.TrackService;

@Singleton
@Path("tracks")
public class TrackController {

    private TrackService trackService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksByPlaylist(
    		@QueryParam("forPlaylist") int playlistId, 
    		@QueryParam("userId") int userId
    		) {
    	return Response
                .status(Response.Status.OK)
                .entity(trackService.getTracksNotInPlaylist(playlistId))
                .build();
    }
    
    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    public TrackService getTrackService() {
        return trackService;
    }
    
}
