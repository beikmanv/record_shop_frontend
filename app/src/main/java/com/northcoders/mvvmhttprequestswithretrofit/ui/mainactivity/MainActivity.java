package com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.mvvmhttprequestswithretrofit.adapter.AlbumAdapter;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.ActivityMainBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.model.AlbumRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    private RecyclerView recyclerView;
    private ArrayList<Album> albums;
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;
    public AlbumRepository albumRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize DataBinding (link the XML layout to this activity)
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Initialize ViewModel (intermediary between View and Model, survives configuration changes (e.g. screen rotations))
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        // Fetch albums from the API
        getAllAlbums();
    }

    // Method to get all albums and observe LiveData (API Call)
    private void getAllAlbums() {
        mainActivityViewModel.getAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                // Cast LiveData to ArrayList
                albums = (ArrayList<Album>) albumsFromLiveData;
                // Call method to display data in RecyclerView
                displayInRecyclerView();
            }
        });
    }

    private void addAlbum(Album album) {
        albumRepository.addAlbum(album);
    }

    // Method to display the albums in RecyclerView
    private void displayInRecyclerView() {
        // Initialize RecyclerView
        recyclerView = binding.recyclerView; // Gets the RecyclerView from the layout
        // Pass the album list to the adapter
        albumAdapter = new AlbumAdapter(albums); // Pass the album list to the adapter
        // Set adapter to RecyclerView
        recyclerView.setAdapter(albumAdapter); // Each album gets displayed as an item
        // Set the layout manager (otherwise RecyclerView doesn't know where or how position the items)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL); // Redundant line, vertical by default
        recyclerView.setLayoutManager(layoutManager);
        // Set fixed size for performance
        recyclerView.setHasFixedSize(true);
        // Notify adapter of data change
        albumAdapter.notifyDataSetChanged();
    }
}
