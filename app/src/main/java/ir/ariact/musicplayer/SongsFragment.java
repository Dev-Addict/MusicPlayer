package ir.ariact.musicplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class SongsFragment extends Fragment {
    private RecyclerView songsRecyclerView;
    private RecyclerView.Adapter songsRecyclerViewAdapter;
    private RecyclerView.LayoutManager songsRecyclerViewLayoutManger;

    public SongsFragment() {
    }

    public static SongsFragment newInstance() {
        SongsFragment fragment = new SongsFragment();
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
        View view = inflater.inflate(R.layout.fragment_songs, container, false);
        songsRecyclerView = view.findViewById(R.id.fragment_songs_songs_recycler_view);
        songsRecyclerViewLayoutManger = new LinearLayoutManager(getActivity());
        songsRecyclerView.setLayoutManager(songsRecyclerViewLayoutManger);
        songsRecyclerViewAdapter = new songsAdapter(getActivity());
        songsRecyclerView.setAdapter(songsRecyclerViewAdapter);
        songsRecyclerView.setHasFixedSize(true);
        songsRecyclerView.setItemViewCacheSize(SongRepository.getInstance().getSongs().size());
        songsRecyclerView.setDrawingCacheEnabled(true);
        return view;
    }
}
