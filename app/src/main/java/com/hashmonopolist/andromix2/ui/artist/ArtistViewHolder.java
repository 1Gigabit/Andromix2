package com.hashmonopolist.andromix2.ui.artist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;

public class ArtistViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView_title;
    private final ImageView imageView_cover;

    public ArtistViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_title = itemView.findViewById(R.id.textview_title);
        imageView_cover = itemView.findViewById(R.id.imageview_album_cover);
    }

    public TextView getTextView_title() {
        return textView_title;
    }

    public ImageView getImageView_cover() {
        return imageView_cover;
    }
}
