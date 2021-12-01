package com.hashmonopolist.andromix2.data.searchresults;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Tracks {
    @Expose
    List<Track> data;

    public List<Track> getData() {
        return data;
    }
}
