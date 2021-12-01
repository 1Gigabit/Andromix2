package com.hashmonopolist.andromix2.ui.artist;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.data.tracklist.Album;
import com.hashmonopolist.andromix2.data.tracklist.TrackList;
import com.hashmonopolist.andromix2.ui.album.AlbumActivity;
import com.squareup.picasso.Picasso;

public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistViewHolder> {
    private final TrackList trackList;

    public ArtistRecyclerAdapter(TrackList trackList) {
        this.trackList = trackList;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_square_album, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Album album = trackList.getReleases().getAlbum().get(position);
        TextView textView_title = holder.getTextView_title();
        ImageView imageView_cover = holder.getImageView_cover();
        textView_title.setText(album.getTitle());
        Picasso.get().load(album.getCover_big()).into(imageView_cover);
        holder.itemView.setOnClickListener(l -> {
            Intent intent = new Intent(holder.itemView.getContext(), AlbumActivity.class);
            intent.putExtra("id", album.getId());
            intent.putExtra("albumTitle", album.getTitle());
            intent.putExtra("albumArtist", trackList.getName());
            intent.putExtra("albumCover", album.getCover_big());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (trackList.getReleases().getAlbum() == null) {
            return 0;
        }
        return trackList.getReleases().getAlbum().size();
    }
}
