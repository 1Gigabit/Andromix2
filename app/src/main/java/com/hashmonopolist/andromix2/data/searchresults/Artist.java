package com.hashmonopolist.andromix2.data.searchresults;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Artist implements Serializable {
    @Expose
    String ART_ID;
    @Expose
    String ART_NAME;
    @Expose
    String ART_PICTURE;

    public String getART_ID() {
        return ART_ID;
    }

    public String getART_NAME() {
        return ART_NAME;
    }

    public String getART_PICTURE() {
        return "https://e-cdns-images.dzcdn.net/images/artist/" + ART_PICTURE + "/512x512-000000-80-0-0.jpg";
    }
}
