package ir.ariact.musicplayer.event;

import ir.ariact.musicplayer.model.Song;
import ir.ariact.musicplayer.model.SongState;

public class SongChangedEvent {
    private Song song;
    private SongState songState;

    public SongChangedEvent(Song song, SongState songState) {
        this.song = song;
        this.songState = songState;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public SongState getSongState() {
        return songState;
    }

    public void setSongState(SongState songState) {
        this.songState = songState;
    }
}
