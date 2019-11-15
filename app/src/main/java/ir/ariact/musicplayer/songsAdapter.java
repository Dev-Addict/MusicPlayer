package ir.ariact.musicplayer;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class songsAdapter extends RecyclerView.Adapter<songsAdapter.SongsViewHolder> {
    Context context;

    songsAdapter(Context context) {
        this.context = context;
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
    public void onBindViewHolder(@NonNull SongsViewHolder holder, final int position) {
        Song currentSong = SongRepository.getInstance().getSong(position);
        holder.title.setText(currentSong.getTitle());
        holder.artist.setText(currentSong.getArtist());
        holder.album.setText(currentSong.getAlbum());
        Log.d("dev", "onBindViewHolder: " + currentSong.getArt());
        Picasso.get().load(currentSong.getArt()).placeholder(R.drawable.ic_action_single_music).into(holder.art);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MusicPlayerService.class);
                intent.putExtra(Vars.getIntentMusicPosition(), position);
                context.startService(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return SongRepository.getInstance().getSongs().size();
    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}
