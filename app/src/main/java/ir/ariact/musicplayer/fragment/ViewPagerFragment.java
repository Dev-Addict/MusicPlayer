package ir.ariact.musicplayer.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.adapter.ContentPagerAdapter;

public class ViewPagerFragment extends Fragment {
    TabLayout categoryTabLayout;
    ViewPager contentViewPager;
    ContentPagerAdapter contentPagerAdapter;
    TabItem songsTabItem, artistsTabItem, albumsTabItem;

    public ViewPagerFragment() {
    }

    public static ViewPagerFragment newInstance() {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        categoryTabLayout = view.findViewById(R.id.fragment_view_pager_category_tab_layout);
        contentViewPager = view.findViewById(R.id.fragment_view_pager_content_view_pager);
        songsTabItem = view.findViewById(R.id.fragment_view_pager_songs_tab_item);
        artistsTabItem = view.findViewById(R.id.fragment_view_pager_artists_tab_item);
        albumsTabItem = view.findViewById(R.id.fragment_view_pager_albums_tab_item);
        contentPagerAdapter = new ContentPagerAdapter(getActivity().getSupportFragmentManager());
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
        return view;
    }
}
