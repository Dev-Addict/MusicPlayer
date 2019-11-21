package ir.ariact.musicplayer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AlbumsFragment extends Fragment {
    RecyclerView albumsRecyclerView;
    RecyclerView.Adapter albumsRecyclerViewAdapter;
    RecyclerView.LayoutManager albumsRecyclerViewLayoutManager;

    public AlbumsFragment() {
    }

    public static AlbumsFragment newInstance() {
        AlbumsFragment fragment = new AlbumsFragment();
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
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        albumsRecyclerView = view.findViewById(R.id.fragment_albums_songs_recycler_view);
        albumsRecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        albumsRecyclerView.setLayoutManager(albumsRecyclerViewLayoutManager);
        albumsRecyclerViewAdapter = new AlbumsAdapter(getActivity());
        albumsRecyclerView.setAdapter(albumsRecyclerViewAdapter);
        albumsRecyclerView.setHasFixedSize(true);
        albumsRecyclerView.setItemViewCacheSize(SongRepository.getInstance().getAlbums().size());
        albumsRecyclerView.setDrawingCacheEnabled(true);
        return view;
    }

}
