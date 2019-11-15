package ir.ariact.musicplayer;

import android.graphics.Bitmap;
import android.net.Uri;

public class Song {
    private long id;
    private Uri songUri;
    private String title, artist, album;
    private Uri art;
    int length;

    public Song(long id, Uri songUri, String title, String artist, String album, Uri art, int length) {
        this.id = id;
        this.songUri = songUri;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.art = art;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Uri getSongUri() {
        return songUri;
    }

    public void setSongUri(Uri songUri) {
        this.songUri = songUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Uri getArt() {
        return art;
    }

    public void setArt(Uri art) {
        this.art = art;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
