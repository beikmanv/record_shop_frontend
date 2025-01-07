package com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationBarView;
import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.adapter.AlbumAdapter;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.ActivityMainBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.model.AlbumRepository;
import com.northcoders.mvvmhttprequestswithretrofit.ui.fragments.DeleteAlbumFragment;
import com.northcoders.mvvmhttprequestswithretrofit.ui.fragments.HomeFragment;
import com.northcoders.mvvmhttprequestswithretrofit.ui.fragments.PostAlbumFragment;
import com.northcoders.mvvmhttprequestswithretrofit.ui.fragments.UpdateAlbumFragment;
import com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

//    private RecyclerView recyclerView;
//    private ArrayList<Album> albumList = new ArrayList<>();
//    private AlbumAdapter albumAdapter;
//    private MainActivityViewModel viewModel;
//    private ActivityMainBinding binding;
//    private AlbumRepository albumRepository;
//    private static final String ALBUM_KEY = "album";
//    private MainActivityClickHandler handler;

    NavigationBarView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set a listener for when the navigation items are selected
        bottomNavigationView.setOnItemSelectedListener(this);

        // This sets the selected item id when the view is created
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    DeleteAlbumFragment deleteAlbumFragment = new DeleteAlbumFragment();
    PostAlbumFragment postAlbumFragment = new PostAlbumFragment();
    UpdateAlbumFragment updateAlbumFragment = new UpdateAlbumFragment();
    HomeFragment homeFragment = new HomeFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            return true;
        } else if (itemId == R.id.delete) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, deleteAlbumFragment)
                    .commit();
            return true;
        } else if (itemId == R.id.post) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, postAlbumFragment)
                    .commit();
            return true;
        } else if (itemId == R.id.update) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, updateAlbumFragment)
                    .commit();
            return true;
        } else {
            return false;
        }
    }


//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
//        handler = new MainActivityClickHandler(this);
//        binding.setClickHandler(handler);
//        getAllAlbums();


//    private void getAllAlbums() {
//        viewModel.getAlbums().observe(this, new Observer<List<Album>>() {
//            @Override
//            public void onChanged(List<Album> albumsFromLiveData) {
//                        albumList = (ArrayList<Album>) albumsFromLiveData;
//                    displayInRecyclerView();
//                }
//        });
//    }

//    private void displayInRecyclerView() {
//        recyclerView = binding.recyclerView;
//        // Pass "this" as the RecyclerViewInterface to handle clicks
//        albumAdapter = new AlbumAdapter(albumList, this);
//        recyclerView.setAdapter(albumAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//        albumAdapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        // Create an Intent to navigate to UpdateAlbumActivity
//        Intent intent = new Intent(MainActivity.this, UpdateAlbumActivity.class);
//        // Pass the selected Album object as a Parcelable
//        intent.putExtra(ALBUM_KEY , albumList.get(position));
//        // Start the UpdateAlbumActivity
//        startActivity(intent);
//    }
}
