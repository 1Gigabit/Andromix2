package com.hashmonopolist.andromix2.ui.main;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.data.searchresults.Artist;
import com.hashmonopolist.andromix2.ui.artist.ArtistActivity;
import com.hashmonopolist.andromix2.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<Artist> artists;

    public ArtistRecyclerAdapter(List<Artist> artists) {
        this.artists = artists;
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
        Artist artist = artists.get(position);
        holder.getTextView_title().setText(artist.getART_NAME());
        holder.getTextView_subtitle().setVisibility(View.GONE);
        holder.getTextView_by().setVisibility(View.GONE);
        Picasso.get().load(artist.getART_PICTURE()).into(holder.getImageView_cover());
        holder.itemView.setOnClickListener(l -> {
            Intent intent = new Intent(l.getContext(), ArtistActivity.class);
            intent.putExtra("id", artist.getART_ID());
            intent.putExtra("title", artist.getART_NAME());
            intent.putExtra("cover", artist.getART_PICTURE());
            l.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

}
