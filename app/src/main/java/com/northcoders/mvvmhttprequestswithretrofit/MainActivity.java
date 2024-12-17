package com.northcoders.mvvmhttprequestswithretrofit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.mvvmhttprequestswithretrofit.adapter.AlbumAdapter;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.model.AlbumRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private AlbumRepository albumRepository;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Adapter with an empty list
        albumAdapter = new AlbumAdapter(new ArrayList<>());
        recyclerView.setAdapter(albumAdapter);

        // Initialize Repository
        albumRepository = new AlbumRepository();

        // Observe data and update UI
        albumRepository.getMutableLiveData().observe(this, albums -> {
            if (albums != null) {
                albumAdapter.setAlbumList(albums); // Update the adapter
            }
        });
    }
}

