package ir.ariact.musicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.event.MoveSongEvent;
import ir.ariact.musicplayer.event.ShowSongControllerEvent;
import ir.ariact.musicplayer.event.SongChangedEvent;
import ir.ariact.musicplayer.fragment.AlbumSongsFragment;
import ir.ariact.musicplayer.fragment.ArtistSongsFragment;
import ir.ariact.musicplayer.model.MoveSongState;
import ir.ariact.musicplayer.model.Song;
import ir.ariact.musicplayer.model.SongRepository;
import ir.ariact.musicplayer.model.SongState;
import ir.ariact.musicplayer.model.Vars;
import ir.ariact.musicplayer.service.MusicPlayerService;

public class SingleFragmentActivity extends AppCompatActivity {
    String fragmentType;
    String artName;
    ImageView artImageView,
            nextImageView,
            prevImageView,
            playPauseImageView;
    TextView songTitleTextView,
            artistTextView,
            albumTextView;
    LinearLayout controllerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        fragmentType = getIntent().getStringExtra(Vars.getIntentFragmentType());
        artName = getIntent().getStringExtra(Vars.getIntentArtName());
        if (fragmentType.equals(Vars.getArtistFragmentType())){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.activity_single_fragment_frame_layout,
                            ArtistSongsFragment
                                    .newInstance(artName))
                    .commit();
        }else{
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.activity_single_fragment_frame_layout,
                            AlbumSongsFragment
                                    .newInstance(artName))
                    .commit();
        }
        artImageView = findViewById(R.id.activity_single_fragment_song_art_circle_image_view);
        nextImageView = findViewById(R.id.activity_single_fragment_next_button_image_view);
        prevImageView = findViewById(R.id.activity_single_fragment_prev_button_image_view);
        playPauseImageView = findViewById(R.id.activity_single_fragment_play_pause_button_image_view);
        songTitleTextView = findViewById(R.id.activity_single_fragment_title_text_view);
        artistTextView = findViewById(R.id.activity_single_fragment_artist_text_view);
        albumTextView = findViewById(R.id.activity_single_fragment_album_text_view);
        controllerLayout = findViewById(R.id.activity_single_fragment_song_controller_layout);
        if (Vars.getSongState() == null){
            controllerLayout.setVisibility(View.GONE);
        } else{
            Song song = SongRepository.getInstance().getSongById(Vars.getSongId());
            SongState songState = Vars.getSongState();
            Picasso.get().load(song.getArt()).placeholder(R.drawable.ic_action_single_music).into(artImageView);
            if (songState.equals(SongState.PLAYING)){
                playPauseImageView.setImageResource(R.drawable.ic_action_pause);
            }else{
                playPauseImageView.setImageResource(R.drawable.ic_action_play);
            }
            songTitleTextView.setText(song.getTitle());
            albumTextView.setText(song.getAlbum());
            artistTextView.setText(song.getArtist());
        }
        overridePendingTransition(
                R.anim.activity_transition_in,
                R.anim.activity_transition_out);
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
        controllerLayout.setVisibility(View.VISIBLE);
        Song song = event.getSong();
        SongState songState = event.getSongState();
        Picasso.get().load(song.getArt()).placeholder(R.drawable.ic_action_single_music).into(artImageView);
        if (songState.equals(SongState.PLAYING)){
            playPauseImageView.setImageResource(R.drawable.ic_action_pause);
        }else{
            playPauseImageView.setImageResource(R.drawable.ic_action_play);
        }
        songTitleTextView.setText(song.getTitle());
        albumTextView.setText(song.getAlbum());
        artistTextView.setText(song.getArtist());
    }

    @Subscribe
    public void showSongController(ShowSongControllerEvent event){
        controllerLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
