package com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.mvvmhttprequestswithretrofit.R;
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
    private MainActivityViewModel viewModel;
    private ActivityMainBinding binding;
    private AlbumRepository albumRepository;
    private static final String ALBUM_KEY = "album";
    private MainActivityClickHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setContentView(binding.getRoot());
        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        handler = new MainActivityClickHandler(this);
        binding.setClickHandler(handler);
        getAllAlbums();
    }

    private void getAllAlbums() {
        viewModel.getAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albumsFromLiveData) {
                        albumList = (ArrayList<Album>) albumsFromLiveData;
                    displayInRecyclerView();
                }
        });
    }

    private void displayInRecyclerView() {
        recyclerView = binding.recyclerView;
        // Pass "this" as the RecyclerViewInterface to handle clicks
        albumAdapter = new AlbumAdapter(albumList, this);
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
