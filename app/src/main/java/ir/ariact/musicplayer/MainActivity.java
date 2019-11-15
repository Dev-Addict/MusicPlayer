package ir.ariact.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout categoryTabLayout;
    ViewPager contentViewPager;
    ContentPagerAdapter contentPagerAdapter;
    TabItem songsTabItem, artistsTabItem, albumsTabItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryTabLayout = findViewById(R.id.activity_main_category_tab_layout);
        contentViewPager = findViewById(R.id.activity_main_content_view_pager);
        songsTabItem = findViewById(R.id.activity_main_songs_tab_item);
        artistsTabItem = findViewById(R.id.activity_main_artists_tab_item);
        albumsTabItem = findViewById(R.id.activity_main_albums_tab_item);
        contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        contentViewPager.setAdapter(contentPagerAdapter);
        contentViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(categoryTabLayout));
        categoryTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("dev", "onTabSelected: " + tab.getPosition());
                contentViewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
