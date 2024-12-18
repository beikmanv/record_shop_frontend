package com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivity;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;

public class AddAlbumClickHandler {

    private Album album;                // The album object
    private Context context;            // The context for creating intents and showing toasts
    private MainActivityViewModel viewModel; // The view model to add the album

    // Constructor to initialize all variables
    public AddAlbumClickHandler(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
    }

    // Handle the Submit button click
    public void onSubmitBtnClicked(View view) {
        // Check if any fields in the album are empty
        if (album.getTitle() == null || album.getTitle().isEmpty() ||
                album.getArtistName() == null || album.getArtistName().isEmpty() ||
                album.getGenre() == null || album.getGenre().isEmpty() ||
                album.getPrice() == 0) {
            // If any field is empty, show a Toast with an error message
            Toast.makeText(context, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
        } else {
            // If all fields are filled, create a new Album object and call addAlbum() from the view model
            viewModel.addAlbum(album);
            // Show a success message
            Toast.makeText(context, "Album added successfully!", Toast.LENGTH_SHORT).show();
            // Navigate back to the MainActivity
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }

    // Handle the Floating Action Button (FAB) click - Navigate back to the MainActivity
    public void onBackBtnClicked(View view) {
        // Simply navigate back to MainActivity without submitting the album
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}