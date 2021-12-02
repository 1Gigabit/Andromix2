package com.hashmonopolist.andromix2.ui.artist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.networking.apis.DeemixAPI;
import com.squareup.picasso.Picasso;

public class ArtistActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private DeemixAPI deemixAPI;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        deemixAPI = new DeemixAPI(this, sharedPreferences.getString("Server", ""), "");
        intent = getIntent();
        RecyclerView recyclerView = findViewById(R.id.recyclerView_albums);
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
        return super.onOptionsItemSelected(item);
    }
}
