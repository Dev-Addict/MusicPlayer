package ir.ariact.musicplayer;


import android.net.Uri;

public class Album {
    private String albumName, artistOfAlbum;
    private int numberOfSongs;
    private Uri art;

    public Album(String albumName, String artistOfAlbum, int numberOfSongs, Uri art) {
        this.albumName = albumName;
        this.artistOfAlbum = artistOfAlbum;
        this.numberOfSongs = numberOfSongs;
        this.art = art;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistOfAlbum() {
        return artistOfAlbum;
    }

    public void setArtistOfAlbum(String artistOfAlbum) {
        this.artistOfAlbum = artistOfAlbum;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public Uri getArt() {
        return art;
    }

    public void setArt(Uri art) {
        this.art = art;
    }
}
