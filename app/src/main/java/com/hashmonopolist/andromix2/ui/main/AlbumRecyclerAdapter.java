package com.hashmonopolist.andromix2.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.data.searchresults.Album;
import com.hashmonopolist.andromix2.ui.album.AlbumActivity;
import com.hashmonopolist.andromix2.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AlbumRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<Album> albumList;
    private final List<View> viewList = new ArrayList<>();
    private boolean selecting = false;

    public AlbumRecyclerAdapter(List<Album> albumList) {
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!viewList.contains(holder.itemView)) viewList.add(holder.itemView);
        Album album = albumList.get(position);
        holder.getTextView_title().setText(album.getALB_TITLE());
        holder.getTextView_subtitle().setText(album.getART_NAME());
        Picasso.get().load(album.getALB_PICTURE()).into(holder.getImageView_cover());
        holder.itemView.setOnClickListener(l -> {
            for (View view : viewList) {
                if (view.isSelected()) {
                    selecting = true;
                    break;
                }
                selecting = false;
            }
            if (!selecting) {
                Intent intent = new Intent(l.getContext(), AlbumActivity.class);
                intent.putExtra("id", album.getALB_ID());
                intent.putExtra("albumTitle", album.getALB_TITLE());
                intent.putExtra("albumArtist", album.getART_NAME());
                intent.putExtra("albumCover", album.getALB_PICTURE());
                l.getContext().startActivity(intent);
            } else {
                l.setSelected(!l.isSelected());
                l.setBackgroundColor(l.isSelected() ? Color.CYAN : Color.TRANSPARENT);
            }

        });
        holder.itemView.setOnLongClickListener(l -> {
            selecting = !selecting;
            l.setSelected(true);
            l.setBackgroundColor(selecting ? Color.CYAN : Color.TRANSPARENT);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public List<Album> getSelectedAlbums() {
        List<Album> trackList = new ArrayList<>();
        for (int i = 0; i < viewList.size(); i++) {
            if (viewList.get(i).isSelected()) trackList.add(this.albumList.get(i));
        }
        return trackList;
    }

}
