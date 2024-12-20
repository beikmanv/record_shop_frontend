package com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.model.Artist;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivity;

public class UpdateAlbumClickHandler {

    private Album album;  // Album object to be updated
    private Context context;  // Context to start activities
    private MainActivityViewModel viewModel;  // ViewModel to handle update operation
    private Long albumId;  // Album id, used for updating the album

    // Constructor to initialize the necessary objects
    public UpdateAlbumClickHandler(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
        Log.d("UpdateAlbumClickHandler", "UpdateAlbumClickHandler initialized for album: " + album.getTitle());
    }

    // Method for the 'Back' button click
    public void onBackBtnClicked(View view) {
        Log.i("UpdateAlbumClickHandler", "Back button clicked, navigating to MainActivity.");
        // Start MainActivity to go back to the album list
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    // Method for the 'Submit' button click (Update button)
    public void onUpdateBtnClicked(View view) {
        Log.i("UpdateAlbumClickHandler", "Update button clicked.");

        // Create an updated Album object with the current data
        Artist updatedArtist = album.getArtist();  // Get the current Artist object
        updatedArtist.setArtistId(album.getArtistId());  // Set the artistId from the Album
        updatedArtist.setArtistName(album.getArtistName());  // Set the artistName from the Album


        // Create an updated Album object with the current data
        Album updatedAlbum = new Album(
                album.getAlbumId(),
                album.getArtistId(),
                album.getArtist(),
                album.getArtistName(),
                album.getTitle(),
                album.getGenre(),
                album.getReleaseYear(),
                album.getStock(),
                album.getPrice(),
                album.getCreatedAt(),
                album.getUpdatedAt(),
                album.getMessage()
//                album.getImageResource()
        );

        // Check if any of the fields are empty or invalid
        if (updatedAlbum.getArtistName().isEmpty() ||
                updatedAlbum.getTitle().isEmpty() ||
                updatedAlbum.getGenre().isEmpty() ||
                updatedAlbum.getPrice() == 0) {
            // Show a Toast message if any of the fields are empty
            Log.w("UpdateAlbumClickHandler", "Validation failed: Fields cannot be empty.");
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            // Start MainActivity after updating the album
            Intent intent = new Intent(context, MainActivity.class);
            Log.d("UpdateAlbumClickHandler", "Updating album with ID: " + album.getAlbumId());
            viewModel.updateAlbum(album.getAlbumId(), updatedAlbum);
            context.startActivity(intent);
        }
    }

    public void onDeleteBtnClicked(View view) {
        Log.i("UpdateAlbumClickHandler", "Delete button clicked for album ID: " + album.getAlbumId());
        // Delete the album
        viewModel.deleteAlbum(album.getAlbumId());
        // Navigate back to MainActivity
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
