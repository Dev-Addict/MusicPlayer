package ir.ariact.musicplayer;

import android.graphics.Bitmap;
import android.net.Uri;

class Artist {
    private String artistName, artistKey;
    private int numberOfAlbum, numberOfTrack;
    Uri art;

    public Artist(String artistName, String artistKey, int numberOfAlbum, int numberOfTrack, Uri art) {
        this.artistName = artistName;
        this.artistKey = artistKey;
        this.numberOfAlbum = numberOfAlbum;
        this.numberOfTrack = numberOfTrack;
        this.art = art;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistKey() {
        return artistKey;
    }

    public void setArtistKey(String artistKey) {
        this.artistKey = artistKey;
    }

    public int getNumberOfAlbum() {
        return numberOfAlbum;
    }

    public void setNumberOfAlbum(int numberOfAlbum) {
        this.numberOfAlbum = numberOfAlbum;
    }

    public int getNumberOfTrack() {
        return numberOfTrack;
    }

    public void setNumberOfTrack(int numberOfTrack) {
        this.numberOfTrack = numberOfTrack;
    }

    public Uri getArt() {
        return art;
    }

    public void setArt(Uri art) {
        this.art = art;
    }
}
