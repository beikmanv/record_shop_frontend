package com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum;

import android.os.Bundle;
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

        // Ensure the Album object was passed correctly
        if (album == null) {
            Toast.makeText(this, "Album data not found", Toast.LENGTH_SHORT).show();
            finish();  // Close the activity if Album data is not available
            return;
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
