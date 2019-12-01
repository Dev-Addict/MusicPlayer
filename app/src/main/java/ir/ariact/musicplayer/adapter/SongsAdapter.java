package ir.ariact.musicplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.LinkedList;
import java.util.List;

import ir.ariact.musicplayer.activity.MainActivity;
import ir.ariact.musicplayer.activity.SongActivity;
import ir.ariact.musicplayer.event.ShowSongControllerEvent;
import ir.ariact.musicplayer.model.SongFilter;
import ir.ariact.musicplayer.model.SongState;
import ir.ariact.musicplayer.service.MusicPlayerService;
import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.model.Song;
import ir.ariact.musicplayer.model.SongRepository;
import ir.ariact.musicplayer.model.Vars;


public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsViewHolder> {
    private Context context;
    private FragmentManager fragmentManager;
    private List<Song> songs;
    private SongFilter songFilter;
    private String filterValue;

    public SongsAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        songs = SongRepository.getInstance().getSongs();
        songFilter = SongFilter.NONE;
        filterValue = "";
    }

    public SongsAdapter(Context context, FragmentManager fragmentManager, String artistName) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        songs = new LinkedList<>();
        for (Song song : SongRepository.getInstance().getSongs()) {
            if (song.getArtist().equals(artistName)){
                songs.add(song);
            }
        }
        songFilter = SongFilter.ARTIST;
        filterValue = artistName;
    }

    public SongsAdapter(Context context, String albumName, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        songs = new LinkedList<>();
        for (Song song : SongRepository.getInstance().getSongs()) {
            if (song.getAlbum().equals(albumName)){
                songs.add(song);
            }
        }
        songFilter = SongFilter.ALBUM;
        filterValue = albumName;
    }

    static class SongsViewHolder extends RecyclerView.ViewHolder{
        TextView title, artist, album;
        ImageView art;
        View view;

        SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.song_row_layout_title);
            artist = itemView.findViewById(R.id.song_row_layout_artist);
            album = itemView.findViewById(R.id.song_row_layout_album);
            art = itemView.findViewById(R.id.song_row_layout_art);
            view = itemView;
        }
    }
    @NonNull
    @Override
    public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View songRowLayout = inflater.inflate(R.layout.song_row_layout, parent, false);
        return new SongsViewHolder(songRowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final SongsViewHolder holder, final int position) {
        final Song currentSong = songs.get(position);
        holder.title.setText(currentSong.getTitle());
        holder.artist.setText(currentSong.getArtist());
        holder.album.setText(currentSong.getAlbum());
        Picasso.get().load(currentSong.getArt()).placeholder(R.drawable.ic_action_single_music).into(holder.art);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ShowSongControllerEvent());
                Vars.setSongFilter(songFilter);
                Vars.setFilterValue(filterValue);
                Intent serviceIntent = new Intent(context, MusicPlayerService.class);
                serviceIntent.putExtra(Vars.getIntentMusicPosition(), currentSong.getId());
                context.startService(serviceIntent);
                Vars.setSongState(SongState.PLAYING);
                MainActivity.startActivity(SongActivity.class);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
