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

import ir.ariact.musicplayer.activity.SingleFragmentActivity;
import ir.ariact.musicplayer.fragment.ArtistSongsFragment;
import ir.ariact.musicplayer.R;
import ir.ariact.musicplayer.model.Artist;
import ir.ariact.musicplayer.model.SongRepository;
import ir.ariact.musicplayer.model.Vars;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder> {
    private Context context;
    private FragmentManager fragmentManager;

    public ArtistsAdapter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
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
        final Artist currentArtist = SongRepository.getInstance().getArtist(position);
        holder.artistName.setText(currentArtist.getArtistName());
        holder.numberOfAlbum.setText(currentArtist.getNumberOfAlbum() + "Albums");
        holder.numberOfTracks.setText(currentArtist.getNumberOfTrack() + "Tracks");
        Picasso.get().load(currentArtist.getArt()).placeholder(R.drawable.ic_action_artist).into(holder.art);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singleFragmentActivityIntent = new Intent(context, SingleFragmentActivity.class);
                singleFragmentActivityIntent.putExtra(Vars.getIntentFragmentType(), Vars.getArtistFragmentType());
                singleFragmentActivityIntent.putExtra(Vars.getIntentArtName(), currentArtist.getArtistName());
                context.startActivity(singleFragmentActivityIntent); }
        });
    }

    @Override
    public int getItemCount() {
        return SongRepository.getInstance().getArtists().size();
    }
}
