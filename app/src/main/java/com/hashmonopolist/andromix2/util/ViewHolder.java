package com.hashmonopolist.andromix2.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView_title;
    private final TextView textView_artist;
    private final TextView textView_by;
    private final TextView textView_num;
    private final ImageView imageView_cover;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView_title = itemView.findViewById(R.id.textview_title);
        this.textView_artist = itemView.findViewById(R.id.textview_subtitle);
        this.textView_by = itemView.findViewById(R.id.textview_by);
        this.imageView_cover = itemView.findViewById(R.id.imageview_cover);
        this.textView_num = itemView.findViewById(R.id.textview_num);
    }

    public TextView getTextView_title() {
        return textView_title;
    }

    public TextView getTextView_subtitle() {
        return textView_artist;
    }

    public ImageView getImageView_cover() {
        return imageView_cover;
    }

    public TextView getTextView_by() {
        return textView_by;
    }

    public TextView getTextView_num() {
        return textView_num;
    }
}
