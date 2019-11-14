package ir.ariact.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout categoryTabLayout;
    ViewPager contentViewPager;
    ContentPagerAdapter contentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryTabLayout = findViewById(R.id.activity_main_category_tab_layout);
        contentViewPager = findViewById(R.id.activity_main_content_view_pager);
        contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        contentViewPager.setAdapter(contentPagerAdapter);
    }
}
