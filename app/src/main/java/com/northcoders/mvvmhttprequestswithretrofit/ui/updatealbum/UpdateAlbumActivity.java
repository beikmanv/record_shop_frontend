package com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.ActivityUpdateAlbumBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {

    private UpdateAlbumClickHandler handler;
    private Album album;  // Album object that will be updated
    private static final String ALBUM_KEY = "album";  // The key for passing the Album object
    private ActivityUpdateAlbumBinding binding;  // The view binding
    private MainActivityViewModel viewModel;  // The ViewModel for the activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_album);  // Set the layout for the activity

        // Retrieve the Album object from the Intent using the key "album"
        album = getIntent().getParcelableExtra(ALBUM_KEY);

        // Retrieve data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            String albumId = intent.getStringExtra("albumId"); // Key must match
            if (albumId != null) {
                // Use the album title to fetch or display data
                Log.d("UpdateAlbumActivity", "Album received: " + album.getTitle());
            } else {
                Toast.makeText(this, "Album ID not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Intent is null", Toast.LENGTH_SHORT).show();
        }

        // Initialize ViewModel using ViewModelProvider
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        // Set up DataBinding for the activity
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_album);
        // Create an instance of UpdateAlbumClickHandler, passing Album, Context, and ViewModel
        handler = new UpdateAlbumClickHandler(album, this, viewModel);
        // Bind the Album object to the layout so that changes reflect in the UI
        binding.setAlbum(album);
        // Bind the click handler to the layout to handle actions like saving the updated album
        binding.setClickHandler(handler);
    }
}
