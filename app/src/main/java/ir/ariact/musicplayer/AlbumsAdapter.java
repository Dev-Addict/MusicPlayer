package ir.ariact.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder> {
    Context context;

    public AlbumsAdapter(Context context) {
        this.context = context;
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
        Album currentAlbum = SongRepository.getInstance().getAlbum(position);
        holder.albumName.setText(currentAlbum.getAlbumName());
        holder.artistOfAlbum.setText(currentAlbum.getArtistOfAlbum());
        holder.numberOfSongs.setText(currentAlbum.getNumberOfSongs() + " Tracks");
        Picasso.get().load(currentAlbum.getArt()).placeholder(R.drawable.ic_action_album).into(holder.art);
    }

    @Override
    public int getItemCount() {
        return SongRepository.getInstance().getAlbums().size();
    }
}
