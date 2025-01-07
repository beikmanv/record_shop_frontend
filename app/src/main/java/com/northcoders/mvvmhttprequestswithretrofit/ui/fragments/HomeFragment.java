package com.northcoders.mvvmhttprequestswithretrofit.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.northcoders.mvvmhttprequestswithretrofit.adapter.AlbumAdapter;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.FragmentHomeBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum.UpdateAlbumActivity;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.RecyclerViewInterface;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewInterface {

    private FragmentHomeBinding binding;
    private MainActivityViewModel viewModel;
    private ArrayList<Album> albumList = new ArrayList<>();
    private AlbumAdapter albumAdapter;

    private static final String ALBUM_KEY = "album";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Set up RecyclerView
        setupRecyclerView();

        // Fetch albums
        getAllAlbums();
    }

    private void setupRecyclerView() {
        albumAdapter = new AlbumAdapter(albumList, this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(albumAdapter);
        binding.recyclerView.setHasFixedSize(true);
    }

    private void getAllAlbums() {
        viewModel.getAlbums().observe(getViewLifecycleOwner(), new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                albumList.clear();
                if (albumsFromLiveData != null) {
                    albumList.addAll(albumsFromLiveData);
                }
                albumAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        // Handle RecyclerView item click
        Album clickedAlbum = albumList.get(position);
        Intent intent = new Intent(getContext(), UpdateAlbumActivity.class);
        intent.putExtra(ALBUM_KEY, clickedAlbum);
        startActivity(intent);
    }
}
