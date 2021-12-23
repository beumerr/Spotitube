package han.ica.dea.domain.dto;

import java.util.ArrayList;

public class PlaylistDto {

    private int id;
    private String name;
    private boolean owner;
    private ArrayList<TrackDto> tracks;

    public PlaylistDto() {}

    public PlaylistDto(int id, String name, boolean owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public boolean isOwner() {
        return owner;
    }
    
    public ArrayList<TrackDto> getTracks() {
        return tracks;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setTracks(ArrayList<TrackDto> tracks) {
        this.tracks = tracks;
    }
    
    public void setOwner(boolean owner) {
        this.owner = owner;
    }

}
