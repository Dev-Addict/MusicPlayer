package ir.ariact.musicplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class songsAdapter extends RecyclerView.Adapter<songsAdapter.SongsViewHolder> {

    public songsAdapter() {
    }

    public static class SongsViewHolder extends RecyclerView.ViewHolder{
        TextView title, artist, album;

        public SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.song_row_layout_title);
            artist = itemView.findViewById(R.id.song_row_layout_artist);
            album = itemView.findViewById(R.id.song_row_layout_album);
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
    public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {
        holder.title.setText(SongRepository.getInstance().getSong(position).getTitle());
        holder.artist.setText(SongRepository.getInstance().getSong(position).getArtist());
        holder.album.setText(SongRepository.getInstance().getSong(position).getAlbum());
    }

    @Override
    public int getItemCount() {
        return SongRepository.getInstance().getSongs().size();
    }
}
