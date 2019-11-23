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

import ir.ariact.musicplayer.activity.MainActivity;
import ir.ariact.musicplayer.activity.SingleFragmentActivity;
import ir.ariact.musicplayer.fragment.AlbumSongsFragment;
import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.model.Album;
import ir.ariact.musicplayer.model.SongRepository;
import ir.ariact.musicplayer.model.Vars;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder> {
    Context context;
    private FragmentManager fragmentManager;

    public AlbumsAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumName, artistOfAlbum, numberOfSongs;
        ImageView art;
        View view;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumName = itemView.findViewById(R.id.artist_row_layout_artist_name);
            artistOfAlbum = itemView.findViewById(R.id.artist_row_layout_number_of_albums);
            numberOfSongs = itemView.findViewById(R.id.artist_row_layout_number_of_tracks);
            art = itemView.findViewById(R.id.artist_row_layout_art);
            view = itemView;
        }
    }
    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View albumRowLayout = inflater.inflate(R.layout.artist_row_layout, parent, false);
        return new AlbumViewHolder(albumRowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        final Album currentAlbum = SongRepository.getInstance().getAlbum(position);
        holder.albumName.setText(currentAlbum.getAlbumName());
        holder.artistOfAlbum.setText(currentAlbum.getArtistOfAlbum());
        holder.numberOfSongs.setText(currentAlbum.getNumberOfSongs() + " Tracks");
        Picasso.get().load(currentAlbum.getArt()).placeholder(R.drawable.ic_action_album).into(holder.art);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singleFragmentActivityIntent = new Intent(context, SingleFragmentActivity.class);
                singleFragmentActivityIntent.putExtra(Vars.getIntentFragmentType(), Vars.getAlbumFragmentType());
                singleFragmentActivityIntent.putExtra(Vars.getIntentArtName(), currentAlbum.getAlbumName());
                context.startActivity(singleFragmentActivityIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return SongRepository.getInstance().getAlbums().size();
    }
}
