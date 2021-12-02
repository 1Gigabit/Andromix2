package com.hashmonopolist.andromix2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.hashmonopolist.andromix2.data.AddToQueueResults;
import com.hashmonopolist.andromix2.data.searchresults.Album;
import com.hashmonopolist.andromix2.data.searchresults.Artist;
import com.hashmonopolist.andromix2.data.searchresults.Track;
import com.hashmonopolist.andromix2.networking.apis.DeemixAPI;
import com.hashmonopolist.andromix2.ui.downloadqueue.DownloadQueueActivity;
import com.hashmonopolist.andromix2.ui.main.AlbumPageFragment;
import com.hashmonopolist.andromix2.ui.main.PageFragmentAdapter;
import com.hashmonopolist.andromix2.ui.main.TrackPageFragment;
import com.hashmonopolist.andromix2.ui.settings.SettingsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private final static String[] TABS = new String[]{"Track", "Album", "Artist"};
    private String server;
    private String ARL;
    private DeemixAPI deemixAPI;
    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<Track> tracks = new ArrayList<>();
    private ArrayList<Artist> artists = new ArrayList<>();
    private ViewPager2 viewPager2;
    private SharedPreferences sharedPreferences;
    private TabLayout tabLayout;

    private static String makeFragmentName(int viewPagerId, int index) {
        return "android:switcher:" + viewPagerId + ":" + index;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewpager2);
        tabLayout = findViewById(R.id.tablayout);
        viewPager2.setAdapter(new PageFragmentAdapter(this, tracks, albums, artists));
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(TABS[position])).attach();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.main_menu_item_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                deemixAPI.mainSearch(query, searchResults -> {
                    Toast.makeText(MainActivity.this, "BBB", Toast.LENGTH_SHORT).show();

                    tracks = (ArrayList<Track>) searchResults.getTRACK().getData();
                    albums = (ArrayList<Album>) searchResults.getALBUM().getData();
                    artists = (ArrayList<Artist>) searchResults.getARTIST().getData();
                    final int tabPosition = tabLayout.getSelectedTabPosition();
                    viewPager2.setAdapter(new PageFragmentAdapter(MainActivity.this, tracks, albums, artists));
                    tabLayout.selectTab(tabLayout.getTabAt(tabPosition));

                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    protected void onResume() {
        server = sharedPreferences.getString("Server", null);
        ARL = sharedPreferences.getString("ARL", null);
        deemixAPI = new DeemixAPI(this, server, ARL);
        deemixAPI.loginARL((response) -> {
        });
        if (sharedPreferences.getBoolean("darkTheme", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
        Toast.makeText(this, "AAA", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_item_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                break;
            case R.id.main_menu_item_download:
                TrackPageFragment trackPageFragment = ((TrackPageFragment) getSupportFragmentManager().findFragmentByTag("f0"));
                AlbumPageFragment albumPageFragment = ((AlbumPageFragment) getSupportFragmentManager().findFragmentByTag("f1"));
                List<Track> selectedTracks = trackPageFragment != null ? trackPageFragment.getSelectedTracks() : new ArrayList<>();
                List<Album> selectedAlbums = albumPageFragment != null ? albumPageFragment.getSelectedTracks() : new ArrayList<>();
                String selectedTracksString = selectedTracks.stream().map(Track::getSNG_TITLE).collect(Collectors.joining("\n"));
                String selectedAlbumsString = selectedAlbums.stream().map(Album::getALB_TITLE).collect(Collectors.joining("\n"));
                new AlertDialog.Builder(this)
                        .setTitle("Are you sure you want to download")
                        .setMessage("---Tracks---\n\n" +
                                selectedTracksString + "\n\n" +
                                "---Albums---\n\n" +
                                selectedAlbumsString)
                        .setPositiveButton("Yes", (dialog, which) -> {
                            for (Track selectedTrack : selectedTracks) {
                                deemixAPI.addToQueue(selectedTrack.getSNG_ID(), "track", new DeemixAPI.AddToQueueResponse() {
                                    @Override
                                    public void onSuccess(String networkResponse) {

                                    }

                                    @Override
                                    public void onFailure(AddToQueueResults addToQueueResults) {

                                    }
                                });
                            }
                            for (Album selectedAlbum : selectedAlbums) {
                                deemixAPI.addToQueue(selectedAlbum.getALB_ID(), "album", new DeemixAPI.AddToQueueResponse() {
                                    @Override
                                    public void onSuccess(String networkResponse) {

                                    }

                                    @Override
                                    public void onFailure(AddToQueueResults addToQueueResults) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton("No", (dialog, which) -> {

                        })
                        .create()
                        .show();
                break;
            case R.id.main_menu_item_downloadQueue:
                startActivity(new Intent(MainActivity.this, DownloadQueueActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
