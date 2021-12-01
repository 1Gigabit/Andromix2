package com.hashmonopolist.andromix2.ui.downloadqueue;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.networking.apis.DeemixAPI;

public class DownloadQueueActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DeemixAPI deemixAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloadqueue);
        recyclerView = findViewById(R.id.recycler_view_downloadqueue);

    }
}
