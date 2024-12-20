package com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
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
    private ArrayList<Album> albumList = new ArrayList<>();
    private AlbumAdapter albumAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding binding;
    private AlbumRepository albumRepository;
    public static final String ALBUM_KEY = "album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Initialize ViewModel
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        // Initialize AlbumRepository
        albumRepository = new AlbumRepository(getApplication());
        // Observe the LiveData and get the list of albums
        getAllAlbums();
    }

    private void getAllAlbums() {
        mainActivityViewModel.getAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                if (albumsFromLiveData != null && !albumsFromLiveData.isEmpty()) {
                    albumList.clear();
                    albumList.addAll(albumsFromLiveData);
                    displayInRecyclerView();
                }
            }
        });
    }

    private void displayInRecyclerView() {
        recyclerView = binding.recyclerView;
        // Pass "this" as the RecyclerViewInterface to handle clicks
        albumAdapter = new AlbumAdapter(this, albumList, this);
        recyclerView.setAdapter(albumAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        albumAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        // Create an Intent to navigate to UpdateAlbumActivity
        Intent intent = new Intent(MainActivity.this, UpdateAlbumActivity.class);
        // Pass the selected Album object as a Parcelable
        intent.putExtra(ALBUM_KEY , albumList.get(position));
        // Start the UpdateAlbumActivity
        startActivity(intent);
    }
}
