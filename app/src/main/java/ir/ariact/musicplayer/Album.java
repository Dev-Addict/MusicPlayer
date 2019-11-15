package ir.ariact.musicplayer;


import android.net.Uri;

public class Album {
    String albumName, artistOfAlbum, albumKey;
    int numberOfSongs, year;
    Uri art;

    public Album(String albumName, String artistOfAlbum, String albumKey, int numberOfSongs, int year, Uri art) {
        this.albumName = albumName;
        this.artistOfAlbum = artistOfAlbum;
        this.albumKey = albumKey;
        this.numberOfSongs = numberOfSongs;
        this.year = year;
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

    public String getAlbumKey() {
        return albumKey;
    }

    public void setAlbumKey(String albumKey) {
        this.albumKey = albumKey;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Uri getArt() {
        return art;
    }

    public void setArt(Uri art) {
        this.art = art;
    }
}
