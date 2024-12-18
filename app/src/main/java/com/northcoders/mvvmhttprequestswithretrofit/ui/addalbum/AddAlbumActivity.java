package com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum;

import android.app.Application;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum.AddAlbumClickHandler;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;

public class AddAlbumActivity extends AppCompatActivity {

    private ActivityAddNewAlbumBinding binding;
    private AddAlbumClickHandler handler;
    private Album album;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_album);
        album = new Album(); // Initialize with a new Album object

        // Assuming you are using a ViewModel for the MainActivity to handle adding albums
        viewModel = new MainActivityViewModel(new Application()); // You should get this from your ViewModelProvider

        handler = new AddAlbumClickHandler(album, this, viewModel); // Initialize the click handler

        binding.setAlbum(album); // Bind the album to the layout
        binding.setHandler(handler); // Set the handler to the layout
    }
}
