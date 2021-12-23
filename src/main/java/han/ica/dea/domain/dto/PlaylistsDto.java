package han.ica.dea.domain.dto;

import java.util.ArrayList;

public class PlaylistsDto {

    private ArrayList<PlaylistDto> playlists;
    private int length;

    public PlaylistsDto() {}

    public PlaylistsDto(ArrayList<PlaylistDto> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public ArrayList<PlaylistDto> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<PlaylistDto> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
