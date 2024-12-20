package com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum;

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
    private ActivityUpdateAlbumBinding binding;  // Binding object for the layout
    private UpdateAlbumClickHandler handler;  // Click handler for actions like update or delete
    private Album album;  // The album object to be updated
    private static final String ALBUM_KEY = "album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up data binding
        album = getIntent().getParcelableExtra(ALBUM_KEY, Album.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_album);


        // Check if the Album is null
        if (album == null) {
            // Log the error to help with debugging
            Log.e("IntentLog", "Album is null, cannot proceed");
            // Show a user-friendly message in the UI (Toast)
            Toast.makeText(this, "Error: Album data not found.", Toast.LENGTH_SHORT).show();
            // Optionally, finish the activity if the album is essential
            finish();
            return; // exit the onCreate method to prevent further actions
        } else {
            // Log the album details for debugging purposes
            Log.d("IntentLog", "Album ID: " + album.getAlbumId());
            Log.d("IntentLog", "Album Title: " + album.getTitle());
        }

        // ViewModel for the activity
        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        // Initialize the click handler and pass the album object, context, and ViewModel
        handler = new UpdateAlbumClickHandler(album, this, viewModel);
        // Bind the album data to the layout
        binding.setAlbum(album);
        // Bind the click handler for actions (e.g., update, delete)
        binding.setClickHandler(handler);
    }


}
