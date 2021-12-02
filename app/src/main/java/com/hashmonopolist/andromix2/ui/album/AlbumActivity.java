package com.hashmonopolist.andromix2.ui.album;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.data.AddToQueueResults;
import com.hashmonopolist.andromix2.data.tracklist.Track;
import com.hashmonopolist.andromix2.networking.apis.DeemixAPI;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private DeemixAPI deemixAPI;
    private Intent intent;
    private AlbumRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        deemixAPI = new DeemixAPI(this, sharedPreferences.getString("Server", ""), sharedPreferences.getString("ARL", ""));
        intent = getIntent();
        RecyclerView recyclerView = findViewById(R.id.recyclerView_tracks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        deemixAPI.getTracksFromAlbumID(intent.getStringExtra("id"), tracks -> {
            adapter = new AlbumRecyclerAdapter(tracks.getTracks());
            recyclerView.setAdapter(adapter);
        });
        deemixAPI.loginARL((response) -> {
        });
        TextView textView_albumTitle = findViewById(R.id.albumTitle);
        TextView textView_artistTitle = findViewById(R.id.albumArtist);
        ImageView imageView_album_cover = findViewById(R.id.imageview_album_cover);
        textView_albumTitle.setText(intent.getStringExtra("albumTitle"));
        textView_artistTitle.setText(intent.getStringExtra("albumArtist"));
        Picasso.get().load(intent.getStringExtra("albumCover")).resize(512, 512).into(imageView_album_cover);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.download_menu_item_download) {
            List<Track> selectedTracks = adapter.getSelectedTracks();
            for (Track track : selectedTracks) {
                deemixAPI.addToQueue(track.getId(), "track", new DeemixAPI.AddToQueueResponse() {
                    @Override
                    public void onSuccess(String networkResponse) {
                        Toast.makeText(AlbumActivity.this, "Added " + track.getTitle() + " to queue", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(AddToQueueResults addToQueueResults) {
                        Toast.makeText(AlbumActivity.this, "Failed to add " + track.getTitle() + " to queue", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
