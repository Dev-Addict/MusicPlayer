package ir.ariact.musicplayer.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.event.MoveSongEvent;
import ir.ariact.musicplayer.event.SongChangedEvent;
import ir.ariact.musicplayer.model.MoveSongState;
import ir.ariact.musicplayer.model.Song;
import ir.ariact.musicplayer.model.SongRepository;
import ir.ariact.musicplayer.model.SongState;
import ir.ariact.musicplayer.model.Vars;
import ir.ariact.musicplayer.service.MusicPlayerService;

public class SongActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView titleTextView,
            artistTextView,
            albumTextView;
    ImageView artImageView,
            nextImageView,
            prevImageView,
            playPauseImageView;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        seekBar = findViewById(R.id.activity_song_seek_bar);
        titleTextView = findViewById(R.id.activity_song_title_text_view);
        artistTextView = findViewById(R.id.activity_song_artist_text_view);
        albumTextView = findViewById(R.id.activity_song_album_text_view);
        artImageView = findViewById(R.id.activity_song_art_image_view);
        nextImageView = findViewById(R.id.activity_song_next_image_view);
        prevImageView = findViewById(R.id.activity_song_prev_image_view);
        playPauseImageView = findViewById(R.id.activity_song_play_pause_image_view);
        SongState songState = Vars.getSongState();
        if (songState.equals(SongState.PLAYING)){
            playPauseImageView.setImageResource(R.drawable.ic_action_pause);
        }else{
            playPauseImageView.setImageResource(R.drawable.ic_action_play);
        }
        Song song = SongRepository.getInstance().getSongById(Vars.getSongId());
        titleTextView.setText(song.getTitle());
        artistTextView.setText(song.getArtist());
        albumTextView.setText(song.getAlbum());
        Picasso.get().load(song.getArt()).placeholder(R.drawable.ic_action_single_music).into(artImageView);
        seekBar.setMax(MusicPlayerService.getMusicPlayer().getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    MusicPlayerService.getMusicPlayer().seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        prevImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MoveSongEvent(MoveSongState.PREV));
            }
        });
        nextImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new MoveSongEvent(MoveSongState.NEXT));
            }
        });
        playPauseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Vars.getSongState().equals(SongState.PLAYING)){
                    MusicPlayerService.pauseSong();
                    Vars.setSongState(SongState.STOPPED);
                    playPauseImageView.setImageResource(R.drawable.ic_action_play);
                }else{
                    MusicPlayerService.resumeSong();
                    Vars.setSongState(SongState.PLAYING);
                    playPauseImageView.setImageResource(R.drawable.ic_action_pause);
                }
            }
        });
        updateSeekBar();
    }

    private void updateSeekBar() {
        seekBar.setProgress(MusicPlayerService.getMusicPlayer().getCurrentPosition());
        handler.postDelayed(runnable, 1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void musicChanged(SongChangedEvent event){
        Song song = event.getSong();
        SongState songState = event.getSongState();
        Picasso.get().load(song.getArt()).placeholder(R.drawable.ic_action_single_music).into(artImageView);
        if (songState.equals(SongState.PLAYING)){
            playPauseImageView.setImageResource(R.drawable.ic_action_pause);
        }else{
            playPauseImageView.setImageResource(R.drawable.ic_action_play);
        }
        titleTextView.setText(song.getTitle());
        albumTextView.setText(song.getAlbum());
        artistTextView.setText(song.getArtist());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Vars.getSongState() != null){
            Song song = SongRepository.getInstance().getSongById(Vars.getSongId());
            SongState songState = Vars.getSongState();
            Picasso.get().load(song.getArt()).placeholder(R.drawable.ic_action_single_music).into(artImageView);
            if (songState.equals(SongState.PLAYING)){
                playPauseImageView.setImageResource(R.drawable.ic_action_pause);
            }else{
                playPauseImageView.setImageResource(R.drawable.ic_action_play);
            }
            titleTextView.setText(song.getTitle());
            albumTextView.setText(song.getAlbum());
            artistTextView.setText(song.getArtist());
        }
    }
}
