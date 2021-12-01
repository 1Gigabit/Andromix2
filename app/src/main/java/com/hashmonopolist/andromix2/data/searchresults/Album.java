package com.hashmonopolist.andromix2.data.searchresults;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Album implements Serializable {
    @Expose
    String ALB_ID;
    @Expose
    String ALB_TITLE;
    @Expose
    String ALB_PICTURE;
    @Expose
    String ART_NAME;
    private boolean isSelected;

    public Album(String ALB_ID, String ALB_TITLE, String ALB_PICTURE, String ART_NAME) {
        this.ALB_ID = ALB_ID;
        this.ALB_TITLE = ALB_TITLE;
        this.ALB_PICTURE = ALB_PICTURE;
        this.ART_NAME = ART_NAME;
    }

    public String getALB_ID() {
        return ALB_ID;
    }

    public String getALB_TITLE() {
        return ALB_TITLE;
    }

    public String getALB_PICTURE() {
        return "https://e-cdns-images.dzcdn.net/images/cover/" + ALB_PICTURE + "/156x156-000000-80-0-0.jpg";
    }

    public String getART_NAME() {
        return ART_NAME;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

