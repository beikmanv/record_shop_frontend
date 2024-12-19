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

    private RecyclerView recyclerView;
    private ArrayList<Album> albums = new ArrayList<>();
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;
    private AlbumRepository albumRepository;
    private MainActivityClickHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize ViewModel
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Initialize AlbumRepository
        albumRepository = new AlbumRepository(getApplication());

        // Set up the click handler
        handler = new MainActivityClickHandler(this);
        binding.setClickHandler(handler);

        // Observe the LiveData and get the list of albums
        getAllAlbums();
    }

    private void getAllAlbums() {
        mainActivityViewModel.getAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                // Ensure you're not resetting the list every time
                if (albumsFromLiveData != null && !albumsFromLiveData.isEmpty()) {
                    albums.clear();
                    albums.addAll(albumsFromLiveData);
                    displayInRecyclerView();
                }
            }
        });
    }

    private void displayInRecyclerView() {
        recyclerView = binding.recyclerView;
        albumAdapter = new AlbumAdapter(albums);
        recyclerView.setAdapter(albumAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        albumAdapter.notifyDataSetChanged();
    }
}
