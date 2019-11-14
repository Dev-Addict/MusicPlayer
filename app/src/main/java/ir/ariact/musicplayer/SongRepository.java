package ir.ariact.musicplayer;

import java.util.LinkedList;
import java.util.List;

public class SongRepository {
    private static final SongRepository ourInstance = new SongRepository();

    private List<Song> songs = new LinkedList<>();

    public static SongRepository getInstance() {
        return ourInstance;
    }

    private SongRepository() {
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Song getSong(int pos){
        return songs.get(pos);
    }

    public void addSong(Song song){
        songs.add(song);
    }
}
