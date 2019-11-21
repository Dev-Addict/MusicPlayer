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

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder> {
    private Context context;

    public ArtistsAdapter(Context context) {
        this.context = context;
    }

    static class ArtistsViewHolder extends RecyclerView.ViewHolder{
        TextView artistName, numberOfAlbum, numberOfTracks;
        ImageView art;
        View view;
        public ArtistsViewHolder(@NonNull View itemView) {
            super(itemView);
            artistName = itemView.findViewById(R.id.artist_row_layout_artist_name);
            numberOfAlbum = itemView.findViewById(R.id.artist_row_layout_number_of_albums);
            numberOfTracks = itemView.findViewById(R.id.artist_row_layout_number_of_tracks);
            art = itemView.findViewById(R.id.artist_row_layout_art);
            view =itemView;
        }
    }

    @NonNull
    @Override
    public ArtistsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View artistRowLayout = inflater.inflate(R.layout.artist_row_layout, parent, false);
        return new ArtistsViewHolder(artistRowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistsViewHolder holder, int position) {
        Artist currentArtist = SongRepository.getInstance().getArtist(position);
        holder.artistName.setText(currentArtist.getArtistName());
        holder.numberOfAlbum.setText(currentArtist.getNumberOfAlbum() + "Albums");
        holder.numberOfTracks.setText(currentArtist.getNumberOfTrack() + "Tracks");
        Picasso.get().load(currentArtist.getArt()).placeholder(R.drawable.ic_action_artist).into(holder.art);
    }

    @Override
    public int getItemCount() {
        return SongRepository.getInstance().getArtists().size();
    }
}
