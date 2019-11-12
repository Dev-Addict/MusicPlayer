package ir.ariact.musicplayer;

import java.util.LinkedList;
import java.util.List;

public class SongRepository {
    private static final SongRepository ourInstance = new SongRepository();

    private List<String> songs = new LinkedList<>();

    public static SongRepository getInstance() {
        return ourInstance;
    }

    private SongRepository() {
    }

    public List<String> getSongs() {
        return songs;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public String getSong(int pos){
        return songs.get(pos);
    }

    public void addSong(String song){
        songs.add(song);
    }
}
