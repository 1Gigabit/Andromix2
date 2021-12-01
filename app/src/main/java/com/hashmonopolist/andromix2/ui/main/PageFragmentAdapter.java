package com.hashmonopolist.andromix2.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.hashmonopolist.andromix2.data.searchresults.Album;
import com.hashmonopolist.andromix2.data.searchresults.Artist;
import com.hashmonopolist.andromix2.data.searchresults.Track;

import java.util.ArrayList;

public class PageFragmentAdapter extends FragmentStateAdapter {
    ArrayList<Track> tracks;
    ArrayList<Album> albums;
    ArrayList<Artist> artists;

    public PageFragmentAdapter(FragmentActivity fragmentActivity,
                               ArrayList<Track> tracks,
                               ArrayList<Album> albums,
                               ArrayList<Artist> artists) {
        super(fragmentActivity);
        this.tracks = tracks;
        this.albums = albums;
        this.artists = artists;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return TrackPageFragment.newInstance(tracks);
            case 1:
                return AlbumPageFragment.newInstance(albums);
            case 2:
                return ArtistPageFragment.newInstance(artists);
        }
        return null;
    }

}
