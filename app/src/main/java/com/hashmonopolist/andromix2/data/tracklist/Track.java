package com.hashmonopolist.andromix2.data.tracklist;

import com.google.gson.annotations.Expose;

public class Track {
    @Expose
    String id;
    @Expose
    String title;
    boolean isSelected;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
