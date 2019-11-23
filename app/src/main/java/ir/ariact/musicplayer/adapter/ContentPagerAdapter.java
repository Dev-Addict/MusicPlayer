package ir.ariact.musicplayer.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ir.ariact.musicplayer.fragment.AlbumsFragment;
import ir.ariact.musicplayer.fragment.ArtistsFragment;
import ir.ariact.musicplayer.fragment.SongsFragment;

public class ContentPagerAdapter extends FragmentPagerAdapter {

    public ContentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return SongsFragment.newInstance();
            case 1:
                return ArtistsFragment.newInstance();
            case 2:
                return AlbumsFragment.newInstance();
        }
        return SongsFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 3;
    }
}
