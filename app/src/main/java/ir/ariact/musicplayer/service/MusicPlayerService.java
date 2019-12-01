package ir.ariact.musicplayer.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ir.ariact.musicplayer.activity.MainActivity;
import ir.ariact.musicplayer.event.MoveSongEvent;
import ir.ariact.musicplayer.event.SongChangedEvent;
import ir.ariact.musicplayer.model.MoveSongState;
import ir.ariact.musicplayer.model.Song;
import ir.ariact.musicplayer.model.SongRepository;
import ir.ariact.musicplayer.model.SongState;
import ir.ariact.musicplayer.model.Vars;


public class MusicPlayerService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static MediaPlayer musicPlayer;
    private static long songId;
    private static int musicPosition;
    private static AudioManager musicManager;

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        Context context;
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat
                .Builder(this,
                Vars.getMusicServiceChannel())
                .setContentTitle("WolfMP")
                .setContentText("music")
                .setContentIntent(notificationPendingIntent)
                .build();
        startForeground(2212, notification);
        if (musicPlayer != null) {
            stopSong();
            musicPlayer.release();
            musicPlayer = null;
        }
        songId = intent.getLongExtra(Vars.getIntentMusicPosition(), SongRepository.getInstance().getSong(1).getId());
        musicPlayer = MediaPlayer.create(this, SongRepository.getInstance().getSongById(songId).getSongUri());
        musicPosition = 0;
        musicPlayer.start();
        musicPlayer.setOnCompletionListener(this);
        musicPlayer.setOnErrorListener(this);
        musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        EventBus.getDefault().post(new SongChangedEvent(SongRepository.getInstance().getSongById(songId), SongState.PLAYING));
        Vars.setSongId(songId);
        return START_STICKY;
    }

    public static void stopSong() {
        musicPlayer.stop();
    }

    public static void pauseSong() {
        musicPlayer.pause();
        musicPosition = musicPlayer.getCurrentPosition();
        EventBus.getDefault().post(new SongChangedEvent(SongRepository.getInstance().getSongById(songId), SongState.STOPPED));
    }

    public static void resumeSong() {
        musicPlayer.seekTo(musicPosition);
        musicPlayer.start();
        EventBus.getDefault().post(new SongChangedEvent(SongRepository.getInstance().getSongById(songId), SongState.PLAYING));
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        moveToNextSong(mediaPlayer);
    }

    private void moveToNextSong(MediaPlayer mediaPlayer) {
        stopSong();
        mediaPlayer.release();
        mediaPlayer = null;
        Song song = SongRepository.getInstance().getNextSongById(songId);
        songId = song.getId();
        musicPlayer = MediaPlayer.create(this, song.getSongUri());
        musicPosition = 0;
        musicPlayer.start();
        musicPlayer.setOnCompletionListener(this);
        musicPlayer.setOnErrorListener(this);
        musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        EventBus.getDefault().post(new SongChangedEvent(SongRepository.getInstance().getSongById(songId), SongState.PLAYING));
        Vars.setSongId(songId);
    }

    private void moveToPrevSong(MediaPlayer mediaPlayer){
        stopSong();
        mediaPlayer.release();
        mediaPlayer = null;
        Song song = SongRepository.getInstance().getPrevSongById(songId);
        songId = song.getId();
        musicPlayer = MediaPlayer.create(this, song.getSongUri());
        musicPosition = 0;
        musicPlayer.start();
        musicPlayer.setOnCompletionListener(this);
        musicPlayer.setOnErrorListener(this);
        musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        EventBus.getDefault().post(new SongChangedEvent(SongRepository.getInstance().getSongById(songId), SongState.PLAYING));
        Vars.setSongId(songId);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Log.e("MediaPlayer Error", "MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Log.e("MediaPlayer Error", "MEDIA ERROR SERVER DIED " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Log.e("MediaPlayer Error", "MEDIA ERROR UNKNOWN " + extra);
                break;
        }
        return false;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    Vars.getMusicServiceChannel(),
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Subscribe
    public void moveSong(MoveSongEvent event) {
        if (event.getMoveSongState().equals(MoveSongState.NEXT)) {
            moveToNextSong(musicPlayer);
        } else {
            moveToPrevSong(musicPlayer);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static MediaPlayer getMusicPlayer() {
        return musicPlayer;
    }
}
