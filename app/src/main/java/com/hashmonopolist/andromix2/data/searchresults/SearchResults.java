package com.hashmonopolist.andromix2.data.searchresults;

import com.google.gson.annotations.Expose;

public class SearchResults {
    @Expose
    Tracks TRACK;
    @Expose
    Artists ARTIST;
    @Expose
    Albums ALBUM;

    public Tracks getTRACK() {
        return TRACK;
    }

    public Artists getARTIST() {
        return ARTIST;
    }

    public Albums getALBUM() {
        return ALBUM;
    }
}
