package com.hashmonopolist.andromix2.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hashmonopolist.andromix2.R;
import com.hashmonopolist.andromix2.data.searchresults.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistPageFragment extends Fragment {
    private RecyclerView recyclerView;

    public ArtistPageFragment() {
    }

    public static ArtistPageFragment newInstance(ArrayList<Artist> artists) {
        ArtistPageFragment artistPageFragment = new ArtistPageFragment();
        Bundle args = new Bundle();
        args.putSerializable("artists", artists);
        artistPageFragment.setArguments(args);
        return artistPageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_page, container, false);
        recyclerView = rootView.findViewById(R.id.layout_page);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        ArtistRecyclerAdapter adapter = new ArtistRecyclerAdapter((List<Artist>) getArguments().getSerializable("artists"));
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
