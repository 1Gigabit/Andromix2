package com.hashmonopolist.andromix2.ui.downloadqueue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.data.queue.QueueResults;
import com.hashmonopolist.andromix2.data.queue.Track;
import com.hashmonopolist.andromix2.util.ViewHolder;
import com.squareup.picasso.Picasso;

public class DownloadQueueRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final QueueResults queueResults;

    public DownloadQueueRecyclerAdapter(QueueResults queueResults) {
        this.queueResults = queueResults;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Track track = queueResults.getQueue().get(queueResults.getQueue().keySet().toArray()[position]);
        holder.getTextView_title().setText(track.getTitle());
        holder.getTextView_subtitle().setText(track.getArtist());
        Picasso.get().load(track.getCover()).into(holder.getImageView_cover());
    }

    @Override
    public int getItemCount() {
        return queueResults.getQueue().size();
    }
}
