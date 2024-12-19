package com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivity;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandler {

    private Album album;
    private Context context;
    private MainActivityViewModel viewModel;

    public AddAlbumClickHandler(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onSubmitBtnClicked(View view) {
        if (album.getTitle().isEmpty() || album.getArtist().getArtistName().isEmpty() || album.getGenre().isEmpty() || album.getPrice() == 0) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.addAlbum(album);
            Toast.makeText(context, "Album added successfully", Toast.LENGTH_SHORT).show();
            ((AddAlbumActivity) context).finish(); // End the activity after submission
        }
    }

    public void onBackBtnClicked(View view) {
        ((AddAlbumActivity) context).finish(); // End activity and go back to MainActivity
    }
}
