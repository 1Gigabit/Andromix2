package com.hashmonopolist.andromix2.data.searchresults;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Track implements Serializable {
    @Expose
    String SNG_ID;
    @Expose
    String SNG_TITLE;
    @Expose
    String ART_NAME;
    @Expose
    String ALB_PICTURE;
    boolean selected;

    public String getSNG_ID() {
        return SNG_ID;
    }

    public String getSNG_TITLE() {
        return SNG_TITLE;
    }

    public String getART_NAME() {
        return ART_NAME;
    }

    public String getALB_PICTURE() {
        return "https://e-cdns-images.dzcdn.net/images/cover/" + ALB_PICTURE + "/156x156-000000-80-0-0.jpg";
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
