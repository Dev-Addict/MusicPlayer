package ir.ariact.musicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.fragment.AlbumSongsFragment;
import ir.ariact.musicplayer.fragment.ArtistSongsFragment;
import ir.ariact.musicplayer.model.Vars;

public class SingleFragmentActivity extends AppCompatActivity {
    String fragmentType;
    String artName;

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
        overridePendingTransition(
                R.anim.activity_transition_in,
                R.anim.activity_transition_out);
    }
}
