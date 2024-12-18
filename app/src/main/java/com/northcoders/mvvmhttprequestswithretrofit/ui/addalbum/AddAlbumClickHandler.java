package com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivity;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandler {

    private Album album; // The album object
    private Context context; // The context
    private MainActivityViewModel viewModel; // The view model to add the album

    public AddAlbumClickHandler(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
    }

    // Submit button click handler
    public void onSubmitBtnClicked(View view) {
        if (album.getTitle().isEmpty() || album.getArtistName().isEmpty() || album.getGenre().isEmpty() || album.getPrice() == 0) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            viewModel.addAlbum(album); // Add album using viewModel
            Toast.makeText(context, "Album added successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent); // Navigate to MainActivity
        }
    }

    // Back button click handler
    public void onBackBtnClicked(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent); // Navigate back to MainActivity
    }
}
