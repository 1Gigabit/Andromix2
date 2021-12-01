package com.hashmonopolist.andromix2.ui.settings;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hashmonopolist.andromix2.R;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new SettingsFragment())
                .commit();
        if (getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true)) {
            new AlertDialog.Builder(this)
                    .setTitle("First time")
                    .setMessage(("Since this is your first time starting Andromix there are a few things to be aware of.\n" +
                            "Andromix does NOT download music to your phone.\n\n" +
                            "It simply requests a deemix server to download to your phone for you!"))
                    .setNeutralButton("Okay", (dialog, which) -> {
                    })
                    .create()
                    .show();
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
        }
    }
}
