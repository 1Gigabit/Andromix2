package com.hashmonopolist.andromix2.ui.downloadqueue;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.networking.apis.DeemixAPI;

public class DownloadQueueActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private RecyclerView recyclerView;
    private DeemixAPI deemixAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloadqueue);
        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        recyclerView = findViewById(R.id.recycler_view_downloadqueue);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        deemixAPI = new DeemixAPI(this, sharedPreferences.getString("Server", ""), "");
        deemixAPI.getQueue(queueResults -> {
            System.out.println(queueResults.getQueue().size());
            recyclerView.setAdapter(new DownloadQueueRecyclerAdapter(queueResults));
        });

    }
}
