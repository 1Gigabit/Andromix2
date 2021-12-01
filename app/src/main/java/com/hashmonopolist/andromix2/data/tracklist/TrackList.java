package com.hashmonopolist.andromix2.data.tracklist;

import com.google.gson.annotations.Expose;

public class TrackList {
    @Expose
    Releases releases;
    @Expose
    String name;

    public Releases getReleases() {
        return releases;
    }

    public String getName() {
        return name;
    }
}
