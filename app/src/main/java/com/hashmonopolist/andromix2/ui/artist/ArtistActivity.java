package com.hashmonopolist.andromix2.ui.artist;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.data.AddToQueueResults;
import com.hashmonopolist.andromix2.data.tracklist.Album;
import com.hashmonopolist.andromix2.networking.apis.DeemixAPI;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private DeemixAPI deemixAPI;
    private Intent intent;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        deemixAPI = new DeemixAPI(this, sharedPreferences.getString("Server", ""), sharedPreferences.getString("ARL", ""));
        deemixAPI.loginARL(networkResponse -> {
        });
        intent = getIntent();
        recyclerView = findViewById(R.id.recyclerView_albums);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        deemixAPI.getAlbumsFromArtistID(intent.getStringExtra("id"), albums -> {
            recyclerView.setAdapter(new ArtistRecyclerAdapter(albums));
        });
        TextView textView_title = findViewById(R.id.textview_artist_title);
        ImageView imageView_cover = findViewById(R.id.imageview_artist_cover);
        textView_title.setText(intent.getStringExtra("title"));
        Picasso.get().load(intent.getStringExtra("cover")).into(imageView_cover);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.download_menu_item_download) {
            List<Album> albumList = ((ArtistRecyclerAdapter) recyclerView.getAdapter()).getSelectedAlbums();
            for (Album album : albumList) {
                System.out.println(album.getId());
                deemixAPI.addToQueue(album.getId(), "album", new DeemixAPI.AddToQueueResponse() {
                    @Override
                    public void onSuccess(String networkResponse) {
                        Toast.makeText(ArtistActivity.this, "Added " + album.getTitle() + " to queue", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(AddToQueueResults addToQueueResults) {
                        Toast.makeText(ArtistActivity.this, "Failed to add " + album.getTitle() + " to queue", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
