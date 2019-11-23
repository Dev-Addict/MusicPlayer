package ir.ariact.musicplayer.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import ir.ariact.musicplayer.model.SongRepository;
import ir.ariact.musicplayer.model.Vars;

public class MusicPlayerService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    private static MediaPlayer musicPlayer;
    private static int songPos;
    private static int musicPosition;
    private static AudioManager musicManager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (musicPlayer != null){
            stopSong();
            musicPlayer.release();
            musicPlayer = null;
        }
        songPos = intent.getIntExtra(Vars.getIntentMusicPosition(), 0);
        musicPlayer = MediaPlayer.create(this, SongRepository.getInstance().getSong(songPos).getSongUri());
        musicPosition = 0;
        musicPlayer.start();
        musicPlayer.setOnCompletionListener(this);
        musicPlayer.setOnErrorListener(this);
        musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        return START_STICKY;
    }
    public static void stopSong(){
        musicPlayer.stop();
    }
    public static void pauseSong(){
        musicPlayer.pause();
        musicPosition = musicPlayer.getCurrentPosition();
    }
    private void resumeSong(){
        musicPlayer.seekTo(musicPosition);
        musicPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopSong();
        mediaPlayer.release();
        mediaPlayer = null;
        songPos ++;
        musicPlayer = MediaPlayer.create(this, SongRepository.getInstance().getSong(songPos).getSongUri());
        musicPosition = 0;
        musicPlayer.start();
        musicPlayer.setOnCompletionListener(this);
        musicPlayer.setOnErrorListener(this);
        musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                Log.d("MediaPlayer Error", "MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                Log.d("MediaPlayer Error", "MEDIA ERROR SERVER DIED " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                Log.d("MediaPlayer Error", "MEDIA ERROR UNKNOWN " + extra);
                break;
        }
        return false;
    }
}
