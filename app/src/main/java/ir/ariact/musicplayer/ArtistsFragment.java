package ir.ariact.musicplayer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ArtistsFragment extends Fragment {
    RecyclerView artistsRecyclerView;
    RecyclerView.Adapter artistsRecyclerViewAdapter;
    RecyclerView.LayoutManager artistsRecyclerViewLayoutManager;

    public ArtistsFragment() {
    }

    public static ArtistsFragment newInstance() {
        ArtistsFragment fragment = new ArtistsFragment();
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
        View view = inflater.inflate(R.layout.fragment_artists, container, false);
        artistsRecyclerView = view.findViewById(R.id.fragment_artists_songs_recycler_view);
        artistsRecyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        artistsRecyclerView.setLayoutManager(artistsRecyclerViewLayoutManager);
        artistsRecyclerViewAdapter = new ArtistsAdapter(getActivity(), getActivity().getSupportFragmentManager());
        artistsRecyclerView.setAdapter(artistsRecyclerViewAdapter);
        artistsRecyclerView.setHasFixedSize(true);
        artistsRecyclerView.setItemViewCacheSize(SongRepository.getInstance().getArtists().size());
        artistsRecyclerView.setDrawingCacheEnabled(true);
        return view;
    }

}
