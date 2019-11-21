package ir.ariact.musicplayer;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;

import java.util.LinkedList;
import java.util.List;

public class SetSongListService extends Service {
    public static final String CONTENT_MEDIA_EXTERNAL_AUDIO_ALBUM_ART = "content://media/external/audio/albumart";

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
        @SuppressLint("Recycle") Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
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
                Uri art = ContentUris.withAppendedId(Uri.parse(CONTENT_MEDIA_EXTERNAL_AUDIO_ALBUM_ART), Long.parseLong(musicCursor.getString(musicCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))));
                songList.add(new Song(thisId, finalUri, thisTitle, thisArtist, thisAlbum, art, thisLength));
            }
            while (musicCursor.moveToNext());
        }
        SongRepository.getInstance().setSongs(songList);
        List<String> albums = new LinkedList<>();
        List<Album> albumList = new LinkedList<>();
        for (Song song : songList) {
            if (albums.contains(song.getAlbum())) {
                for (Album album : albumList) {
                    if (album.getAlbumName().equals(song.getAlbum())) {
                        album.setNumberOfSongs(album.getNumberOfSongs() + 1);
                        break;
                    }
                }
            } else {
                albums.add(song.getAlbum());
                albumList.add(new Album(song.getAlbum(), song.getArtist(), 1, song.getArt()));
            }
        }
        SongRepository.getInstance().setAlbums(albumList);
        albums = new LinkedList<>();
        List<String> artists = new LinkedList<>();
        List<Artist> artistList = new LinkedList<>();
        for (Song song : songList) {
            if (artists.contains(song.getArtist())) {
                for (Artist artist : artistList) {
                    if (artist.getArtistName().equals(song.getArtist())) {
                        artist.setNumberOfTrack(artist.getNumberOfTrack() + 1);
                        break;
                    }
                }
            } else {
                artists.add(song.getArtist());
                artistList.add(new Artist(song.getArtist(), 0, 1, song.getArt()));
            }
            if (!albums.contains(song.getAlbum())) {
                albums.add(song.getAlbum());
                for (Artist artist : artistList) {
                    if (artist.getArtistName().equals(song.getArtist())) {
                        artist.setNumberOfAlbum(artist.getNumberOfAlbum() + 1);
                        break;
                    }
                }
            }
        }
        SongRepository.getInstance().setArtists(artistList);
        stopSelf();
        return START_NOT_STICKY;
    }
}
