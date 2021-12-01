package com.hashmonopolist.andromix2.data.searchresults;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Artists {
    @Expose
    List<Artist> data;

    public List<Artist> getData() {
        return data;
    }
}
