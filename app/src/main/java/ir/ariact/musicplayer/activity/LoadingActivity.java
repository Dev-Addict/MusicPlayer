package ir.ariact.musicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.service.SetSongListService;

public class LoadingActivity extends AppCompatActivity {
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Intent setSongServiceIntent = new Intent(this, SetSongListService.class);
        startService(setSongServiceIntent);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainActivityIntent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        }, 5000);
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
