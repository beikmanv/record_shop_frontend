package com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity;

import android.content.Intent;
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
import com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private ArrayList<Album> albums = new ArrayList<>();
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;
    private AlbumRepository albumRepository;
    private MainActivityClickHandler handler;
    public static final String ALBUM_KEY = "album_key";

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

    @Override
    public void onItemClick(int position) {
        // Get the selected album based on the position
        Album selectedAlbum = albums.get(position);
        // Create an intent to open the UpdateAlbumActivity
        Intent intent = new Intent(MainActivity.this, UpdateAlbumActivity.class);
        // Use putExtra (Parcelable method) to pass the Album object to the next activity
        intent.putExtra(ALBUM_KEY, selectedAlbum);
        // Start the UpdateAlbumActivity
        startActivity(intent);
    }
}
