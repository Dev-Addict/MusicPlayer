package ir.ariact.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicPlayerService extends Service {
    private MediaPlayer musicPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        musicPlayer = MediaPlayer.create(this, (Uri) intent.getSerializableExtra(Vars.getIntentMusicUri()));
        musicPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        musicPlayer.stop();
        super.onDestroy();
    }
}
