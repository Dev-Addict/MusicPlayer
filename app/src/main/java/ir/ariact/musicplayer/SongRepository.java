package ir.ariact.musicplayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SongRepository {
    private static final SongRepository ourInstance = new SongRepository();

    private List<Song> songs = new LinkedList<>();
    private List<Album> albums = new LinkedList<>();
    private List<Artist> artists = new LinkedList<>();

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
        return songs.get(pos % songs.size());
    }

    public void addSong(Song song){
        songs.add(song);
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Album getAlbum(int pos){
        return albums.get(pos);
    }

    public void addAlbum(Album album){
        albums.add(album);
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Artist getArtist(int pos){
        return artists.get(pos);
    }

    public void addArtist(Artist artist){
        artists.add(artist);
    }
}
