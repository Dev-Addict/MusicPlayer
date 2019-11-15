package ir.ariact.musicplayer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SetSongListService extends Service {
    public static final String CONTENT_MEDIA_EXTERNAL_AUDIO_ALBUMART = "content://media/external/audio/albumart";
    public SetSongListService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        List<Song> songList = new LinkedList<>();
        ContentResolver musicResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        if (musicCursor != null && musicCursor.moveToFirst()) {
            int titleColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.ARTIST);
            int albumColumn = musicCursor.getColumnIndex
                    (MediaStore.Audio.Media.ALBUM);
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                String thisAlbum = musicCursor.getString(albumColumn);
                int thisLength = Integer.parseInt(musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                Uri finalUri = Uri.withAppendedPath(musicUri, "" + thisId);
                Uri art = ContentUris.withAppendedId(Uri.parse(CONTENT_MEDIA_EXTERNAL_AUDIO_ALBUMART), Long.parseLong(musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))));
                songList.add(new Song(thisId, finalUri, thisTitle, thisArtist, thisAlbum, art, thisLength));
            }
            while (musicCursor.moveToNext());
        }
        SongRepository.getInstance().setSongs(songList);
        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Audio.AlbumColumns.ALBUM
                , MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS,
                MediaStore.Audio.AlbumColumns.FIRST_YEAR,
                MediaStore.Audio.AlbumColumns.ALBUM_KEY,
                MediaStore.Audio.AlbumColumns.ARTIST};
        Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null);
        while (cursor.moveToNext()) {
            String albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM));
            String artistOfAlbum = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ARTIST));
            String albumKey = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_KEY));
            int year = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.FIRST_YEAR));
            int numberOfSongs = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.NUMBER_OF_SONGS));
//            Uri art = ContentUris.withAppendedId(Uri.parse(CONTENT_MEDIA_EXTERNAL_AUDIO_ALBUMART), Long.parseLong(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))));
            SongRepository.getInstance().addAlbum(new Album(albumName, artistOfAlbum, albumKey, numberOfSongs, year, null));
        }
        cursor.close();

        Uri uri2 = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        String[] projection2 = {
                MediaStore.Audio.ArtistColumns.ARTIST
                , MediaStore.Audio.ArtistColumns.ARTIST_KEY,
                MediaStore.Audio.ArtistColumns.NUMBER_OF_ALBUMS
                , MediaStore.Audio.ArtistColumns.NUMBER_OF_TRACKS
        };
        Cursor cursor2 = this.getContentResolver().query(uri2, projection2, null, null, null);
        while (cursor2.moveToNext()) {
            String artistName = cursor2.getString(cursor2.getColumnIndex(MediaStore.Audio.ArtistColumns.ARTIST));
            String artistKey = cursor2.getString(cursor2.getColumnIndex(MediaStore.Audio.ArtistColumns.ARTIST_KEY));
            int numberOfAlbum = cursor2.getInt(cursor2.getColumnIndex(MediaStore.Audio.ArtistColumns.NUMBER_OF_ALBUMS));
            int numberOfTrack = cursor2.getInt(cursor2.getColumnIndex(MediaStore.Audio.ArtistColumns.NUMBER_OF_TRACKS));
//            Uri art = ContentUris.withAppendedId(Uri.parse(CONTENT_MEDIA_EXTERNAL_AUDIO_ALBUMART), Long.parseLong(cursor2.getString(cursor2.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))));
            SongRepository.getInstance().addArtist(new Artist(artistName, artistKey, numberOfAlbum, numberOfTrack, null));
        }
        cursor2.close();
        stopSelf();
        return START_NOT_STICKY;
    }
}
