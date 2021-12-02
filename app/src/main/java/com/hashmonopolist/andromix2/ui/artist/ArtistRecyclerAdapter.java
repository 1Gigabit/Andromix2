package com.hashmonopolist.andromix2.ui.artist;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistViewHolder> {
    private final List<View> viewList = new ArrayList<>();
    private final TrackList trackList;
    private boolean selecting;

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
        if (!viewList.contains(holder.itemView)) viewList.add(holder.itemView);
        Album album = trackList.getReleases().getAlbum().get(position);
        TextView textView_title = holder.getTextView_title();
        ImageView imageView_cover = holder.getImageView_cover();
        textView_title.setText(album.getTitle());
        Picasso.get().load(album.getCover_big()).into(imageView_cover);
        holder.itemView.setOnClickListener(l -> {
            if (!selecting) {
                Intent intent = new Intent(holder.itemView.getContext(), AlbumActivity.class);
                intent.putExtra("id", album.getId());
                intent.putExtra("albumTitle", album.getTitle());
                intent.putExtra("albumArtist", trackList.getName());
                intent.putExtra("albumCover", album.getCover_big());
                holder.itemView.getContext().startActivity(intent);
            }

        });
        holder.itemView.setOnLongClickListener(l -> {
            l.setSelected(!l.isSelected());
            l.setBackgroundColor(l.isSelected() ? Color.CYAN : Color.TRANSPARENT);
            selecting = true;
            return true;
        });
        holder.itemView.setOnClickListener(l -> {
            for (View view : viewList) {
                if (view.isSelected()) {
                    selecting = true;
                    break;
                }
                selecting = false;
            }
            if (selecting) {
                l.setSelected(!l.isSelected());
                l.setBackgroundColor(l.isSelected() ? Color.CYAN : Color.TRANSPARENT);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (trackList.getReleases().getAlbum() == null) {
            return 0;
        }
        return trackList.getReleases().getAlbum().size();
    }

    public List<Album> getSelectedAlbums() {
        List<Album> albumList = new ArrayList<>();
        for (int i = 0; i < viewList.size(); i++) {
            if (viewList.get(i).isSelected())
                albumList.add(this.trackList.getReleases().getAlbum().get(i));
        }
        return albumList;
    }
}
