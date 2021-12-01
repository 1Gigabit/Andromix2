package com.hashmonopolist.andromix2.data.searchresults;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Albums {
    @Expose
    List<Album> data;

    public List<Album> getData() {
        return data;
    }
}
