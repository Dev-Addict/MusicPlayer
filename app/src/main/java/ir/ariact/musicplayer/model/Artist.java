package ir.ariact.musicplayer.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class Artist {
    private String artistName;
    private int numberOfAlbum, numberOfTrack;
    Uri art;

    public Artist(String artistName, int numberOfAlbum, int numberOfTrack, Uri art) {
        this.artistName = artistName;
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
