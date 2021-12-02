package com.hashmonopolist.andromix2.data.queue;

import com.google.gson.annotations.Expose;

public class Track {
    @Expose
    String title;
    String artist;
    String cover;

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getCover() {
        return cover;
    }
}
