package com.hashmonopolist.andromix2.ui.main;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.data.searchresults.Track;
import com.hashmonopolist.andromix2.util.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrackRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<Track> tracks;
    private final List<View> viewList = new ArrayList<>();
    public List<Track> selectedTracks;
    private boolean selecting;

    public TrackRecyclerAdapter(List<Track> tracks) {
        this.tracks = tracks;
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
        Track track = tracks.get(position);
        holder.getTextView_title().setText(track.getSNG_TITLE());
        holder.getTextView_subtitle().setText(track.getART_NAME());
        Picasso.get().load(track.getALB_PICTURE()).into(holder.getImageView_cover());
        holder.itemView.setOnLongClickListener(l -> {
            l.setSelected(!l.isSelected());
            l.setBackgroundColor(l.isSelected() ? Color.CYAN : Color.TRANSPARENT);
            selecting = true;
            selectedTracks = getSelectedTracks();
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
            selectedTracks = getSelectedTracks();
        });
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public List<Track> getSelectedTracks() {
        List<Track> trackList = new ArrayList<>();
        for (int i = 0; i < viewList.size(); i++) {
            if (viewList.get(i).isSelected()) trackList.add(tracks.get(i));
        }
        return trackList;
    }
}
