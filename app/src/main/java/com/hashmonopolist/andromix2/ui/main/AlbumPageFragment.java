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
import com.hashmonopolist.andromix2.data.searchresults.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumPageFragment extends Fragment implements View.OnContextClickListener {
    private RecyclerView recyclerView;
    private AlbumRecyclerAdapter adapter;

    public AlbumPageFragment() {

    }

    public static AlbumPageFragment newInstance(ArrayList<Album> albums) {
        AlbumPageFragment albumPageFragment = new AlbumPageFragment();
        Bundle args = new Bundle();
        args.putSerializable("albums", albums);
        albumPageFragment.setArguments(args);
        return albumPageFragment;
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
        adapter = new AlbumRecyclerAdapter((List<Album>) getArguments().getSerializable("albums"));
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean onContextClick(View v) {
        return false;
    }

    public List<Album> getSelectedTracks() {
        return adapter.getSelectedAlbums();
    }

}
