package com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.handler.AddNewAlbumClickHandler;

public class AddNewAlbumActivity extends AppCompatActivity {

    private ActivityAddNewAlbumBinding binding;
    private AddNewAlbumClickHandler handler;
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_album);
        album = new Album(); // Initialize with a new Album object

        handler = new AddNewAlbumClickHandler(); // Initialize the click handler

        binding.setAlbum(album); // Bind the album to the layout
        binding.setHandler(handler); // Set the handler to the layout
    }
}
