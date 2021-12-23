package han.ica.dea.domain.dto;

import java.util.ArrayList;

public class TracksDto {

    private ArrayList<TrackDto> tracks = new ArrayList<>();

    public TracksDto() {}

    public TracksDto(ArrayList<TrackDto> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<TrackDto> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackDto> tracks) {
        this.tracks = tracks;
    }

}
