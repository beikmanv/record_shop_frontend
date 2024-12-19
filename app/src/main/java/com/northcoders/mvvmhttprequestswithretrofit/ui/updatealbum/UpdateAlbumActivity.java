package com.northcoders.mvvmhttprequestswithretrofit.view;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.ActivityUpdateAlbumBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;
import com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum.UpdateAlbumClickHandler;

public class UpdateAlbumActivity extends AppCompatActivity {

    private UpdateAlbumClickHandler handler;
    private Album album;
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

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Set up DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_album);

        // Create an instance of UpdateAlbumClickHandler (pass the Album and ViewModel to the handler)
        handler = new UpdateAlbumClickHandler(album, this, viewModel);

        // Bind the Album object to the layout
        binding.setAlbum(album);

        // Bind the click handler to the layout (to handle actions like saving the updated album)
        binding.setClickHandler(handler);
    }
}
