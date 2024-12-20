package com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.northcoders.mvvmhttprequestswithretrofit.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.model.Artist;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivity;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandler {

    private Album album;
    private Context context;
    private MainActivityViewModel viewModel;
    private ActivityAddNewAlbumBinding binding;

    public AddAlbumClickHandler(Album album, Context context, MainActivityViewModel viewModel, ActivityAddNewAlbumBinding binding) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
        this.binding = binding;
    }

    public void onSubmitBtnClicked(View view) {
        String artistName = binding.editArtistName.getText().toString().trim();
        if (album.getTitle().isEmpty() || album.getArtist().getArtistName().isEmpty() || album.getGenre().isEmpty() || album.getPrice() == 0) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            Artist artist = new Artist();
            artist.setArtistName(artistName);
            album.setArtist(artist);

            Intent intent = new Intent(context, MainActivity.class);

            viewModel.addAlbum(album);
            context.startActivity(intent);
//            Toast.makeText(context, "Album added successfully", Toast.LENGTH_SHORT).show();
//            ((AddAlbumActivity) context).finish(); // End the activity after submission
        }
    }

    public void onBackBtnClicked(View view) {
        ((AddAlbumActivity) context).finish(); // End activity and go back to MainActivity
    }
}
