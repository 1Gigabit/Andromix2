package com.hashmonopolist.andromix2.data.tracklist;

import com.google.gson.annotations.Expose;

public class Album {
    @Expose
    String id;
    @Expose
    String title;
    @Expose
    String cover_big;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCover_big() {
        return cover_big;
    }
}
