package com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum;

import android.app.Application;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;

public class AddAlbumActivity extends AppCompatActivity {

    private ActivityAddNewAlbumBinding binding;
    private AddAlbumClickHandler handler;
    private Album album;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_album);

        // Initialize the album object
        album = new Album();

        // Get ViewModel instance using ViewModelProvider
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        // Initialize the handler
        handler = new AddAlbumClickHandler(album, this, viewModel);

        // Bind the album and handler to the layout
        binding.setAlbum(album);
        binding.setHandler(handler);
    }
}
