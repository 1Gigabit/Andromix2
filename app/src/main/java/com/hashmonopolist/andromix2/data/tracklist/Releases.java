package com.hashmonopolist.andromix2.data.tracklist;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Releases {
    @Expose
    List<Album> album;

    public List<Album> getAlbum() {
        return album;
    }
}
