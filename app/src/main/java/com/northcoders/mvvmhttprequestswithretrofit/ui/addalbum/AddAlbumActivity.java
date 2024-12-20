package com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.model.Artist;
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
//        album = new Album(album.getAlbumId(), album.getArtistId(), album.getArtist(), album.getArtistName(),
//                album.getTitle(), album.getGenre(), album.getReleaseYear(), album.getStock(), album.getPrice(),
//                album.getCreatedAt(), album.getUpdatedAt(), album.getMessage());
        album = new Album();
        album.setArtist(new Artist());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        clickHandler = new AddAlbumClickHandler(album, this, viewModel, binding);
        binding.setAlbum(album);
        binding.setClickHandler(clickHandler);
    }
}
