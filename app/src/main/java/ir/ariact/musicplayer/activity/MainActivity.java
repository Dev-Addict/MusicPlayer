package ir.ariact.musicplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.fragment.ViewPagerFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.activity_main_fragment_layout,
                        ViewPagerFragment
                                .newInstance())
                .commit();
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(
                        R.id.activity_main_fragment_layout,
                        ViewPagerFragment
                                .newInstance())
                .commit();
    }
}
