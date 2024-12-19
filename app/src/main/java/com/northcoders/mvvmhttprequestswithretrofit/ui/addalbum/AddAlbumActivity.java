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
    private AddAlbumClickHandler clickHandler;
    private Album album;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_album);
        album = new Album();
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        clickHandler = new AddAlbumClickHandler(album, this, viewModel);
        binding.setAlbum(album);
        binding.setClickHandler(clickHandler);
    }
}
