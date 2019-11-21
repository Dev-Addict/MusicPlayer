package ir.ariact.musicplayer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArtistSongsFragment extends Fragment {
    private RecyclerView songsRecyclerView;
    private RecyclerView.Adapter songsRecyclerViewAdapter;
    private RecyclerView.LayoutManager songsRecyclerViewLayoutManger;

    private static final String ARG_ARTIST_NAME = "artist name";

    String artistName;

    public ArtistSongsFragment() {
    }

    public static ArtistSongsFragment newInstance(String artist) {
        ArtistSongsFragment fragment = new ArtistSongsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putString(ARG_ARTIST_NAME, artist);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artistName = "";
        if (getArguments() != null) {
            artistName = getArguments().getString(ARG_ARTIST_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist_songs, container, false);
        songsRecyclerView = view.findViewById(R.id.fragment_artist_songs_songs_recycler_view);
        songsRecyclerViewLayoutManger = new LinearLayoutManager(getActivity());
        songsRecyclerView.setLayoutManager(songsRecyclerViewLayoutManger);
        songsRecyclerViewAdapter = new SongsAdapter(getActivity(), getActivity().getSupportFragmentManager(), artistName);
        songsRecyclerView.setAdapter(songsRecyclerViewAdapter);
        songsRecyclerView.setHasFixedSize(true);
        songsRecyclerView.setItemViewCacheSize(SongRepository.getInstance().getSongs().size());
        songsRecyclerView.setDrawingCacheEnabled(true);
        return view;
    }
}
