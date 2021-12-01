package com.hashmonopolist.andromix2.ui.album;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.data.tracklist.Track;
import com.hashmonopolist.andromix2.util.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class AlbumRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final List<Track> trackList;
    private final List<View> viewList = new ArrayList<>();
    private boolean selecting;

    public AlbumRecyclerAdapter(List<Track> trackList) {
        this.trackList = trackList;
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
        Track track = trackList.get(position);
        TextView textView_title = holder.getTextView_title();
        TextView textView_num = holder.getTextView_num();
        textView_title.setText(track.getTitle());
        holder.getTextView_subtitle().setVisibility(View.GONE);
        holder.getImageView_cover().setVisibility(View.GONE);
        holder.getTextView_by().setVisibility(View.GONE);
        textView_num.setVisibility(View.VISIBLE);
        textView_num.setText(String.valueOf((position + 1)));
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

    public List<Track> getSelectedTracks() {
        List<Track> trackList = new ArrayList<>();
        for (int i = 0; i < viewList.size(); i++) {
            if (viewList.get(i).isSelected()) trackList.add(this.trackList.get(i));
        }
        return trackList;
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
