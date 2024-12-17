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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    private RecyclerView recyclerView;
    private ArrayList<Album> albums = new ArrayList<>();
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;

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

    // Method to display the albums in RecyclerView
    private void displayInRecyclerView() {
        // Initialize RecyclerView
        recyclerView = binding.recyclerView; // Gets the RecyclerView from the layout
        albumAdapter = new AlbumAdapter(albums); // Pass the album list to the adapter
        // Set adapter to RecyclerView
        recyclerView.setAdapter(albumAdapter);
        // Set the layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Set fixed size for performance
        recyclerView.setHasFixedSize(true);
        // Notify adapter of data change
        albumAdapter.notifyDataSetChanged();
    }
}
