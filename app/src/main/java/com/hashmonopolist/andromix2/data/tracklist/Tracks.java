package com.hashmonopolist.andromix2.data.tracklist;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Tracks {
    @Expose
    List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }
}
