package ir.ariact.musicplayer.model;

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

    public Song getSong(int pos) {
        return songs.get(pos % songs.size());
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public Song getSongById(long id) {
        for (Song song : songs) {
            if (song.getId() == id) {
                return song;
            }
        }
        return null;
    }

    public Song getNextSongById(long id) {
        if (Vars.getSongFilter().equals(SongFilter.NONE)) {
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getId() == id) {
                    return songs.get((i + 1) % songs.size());
                }
            }
        } else if (Vars.getSongFilter().equals(SongFilter.ARTIST)) {
            List<Song> artistSongs = new ArrayList<>();
            for (Song song : songs) {
                if (song.getArtist().equals(Vars.getFilterValue())) {
                    artistSongs.add(song);
                }
            }
            for (int i = 0; i < artistSongs.size(); i++) {
                if (artistSongs.get(i).getId() == id){
                    return artistSongs.get((i + 1) % artistSongs.size());
                }
            }
        } else {
            List<Song> albumSongs = new ArrayList<>();
            for (Song song : songs) {
                if (song.getArtist().equals(Vars.getFilterValue())) {
                    albumSongs.add(song);
                }
            }
            for (int i = 0; i < albumSongs.size(); i++) {
                if (albumSongs.get(i).getId() == id){
                    return albumSongs.get((i + 1) % albumSongs.size());
                }
            }
        }
        return null;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Album getAlbum(int pos) {
        return albums.get(pos);
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public Artist getArtist(int pos) {
        return artists.get(pos);
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
    }

    public Song getPrevSongById(long id) {
        if (Vars.getSongFilter().equals(SongFilter.NONE)) {
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getId() == id) {
                    return songs.get((i - 1 + songs.size()) % songs.size());
                }
            }
        } else if (Vars.getSongFilter().equals(SongFilter.ARTIST)) {
            List<Song> artistSongs = new ArrayList<>();
            for (Song song : songs) {
                if (song.getArtist().equals(Vars.getFilterValue())) {
                    artistSongs.add(song);
                }
            }
            for (int i = 0; i < artistSongs.size(); i++) {
                if (artistSongs.get(i).getId() == id){
                    return artistSongs.get((i - 1 + songs.size()) % artistSongs.size());
                }
            }
        } else {
            List<Song> albumSongs = new ArrayList<>();
            for (Song song : songs) {
                if (song.getArtist().equals(Vars.getFilterValue())) {
                    albumSongs.add(song);
                }
            }
            for (int i = 0; i < albumSongs.size(); i++) {
                if (albumSongs.get(i).getId() == id){
                    return albumSongs.get((i - 1 + songs.size()) % albumSongs.size());
                }
            }
        }
        return null;
    }
}
