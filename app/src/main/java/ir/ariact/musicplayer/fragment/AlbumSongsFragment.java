package ir.ariact.musicplayer.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.adapter.SongsAdapter;
import ir.ariact.musicplayer.model.SongRepository;

public class AlbumSongsFragment extends Fragment {
    private static final String ARG_ALNUM_NANE = "album name";
    private RecyclerView songsRecyclerView;
    private RecyclerView.Adapter songsRecyclerViewAdapter;
    private RecyclerView.LayoutManager songsRecyclerViewLayoutManger;

    String albumName;

    public AlbumSongsFragment() {
    }

    public static AlbumSongsFragment newInstance(String albumName) {
        AlbumSongsFragment fragment = new AlbumSongsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString(ARG_ALNUM_NANE, albumName);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumName = "";
        if (getArguments() != null) {
            albumName = getArguments().getString(ARG_ALNUM_NANE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_songs, container, false);
        songsRecyclerView = view.findViewById(R.id.fragment_album_songs_songs_recycler_view);
        songsRecyclerViewLayoutManger = new LinearLayoutManager(getActivity());
        songsRecyclerView.setLayoutManager(songsRecyclerViewLayoutManger);
        songsRecyclerViewAdapter = new SongsAdapter(getActivity(), albumName , getActivity().getSupportFragmentManager());
        songsRecyclerView.setAdapter(songsRecyclerViewAdapter);
        songsRecyclerView.setHasFixedSize(true);
        songsRecyclerView.setItemViewCacheSize(SongRepository.getInstance().getSongs().size());
        songsRecyclerView.setDrawingCacheEnabled(true);
        return view;
    }


}
