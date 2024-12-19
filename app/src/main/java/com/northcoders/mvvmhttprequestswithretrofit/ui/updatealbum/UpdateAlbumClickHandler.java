package com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivityViewModel;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.MainActivity;

import java.util.Objects;


public class UpdateAlbumClickHandler {

    private Album album;  // Album object that will be updated or deleted
    private Context context;  // Context to start activities
    private MainActivityViewModel viewModel;  // ViewModel to handle update and delete operations
    private int id;  // Album id, used for updating or deleting the album

    // Constructor to initialize the necessary objects
    public UpdateAlbumClickHandler(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
        this.id = album.getAlbumId();  // Set the albumId for the current album
    }

    // Method for the 'Back' button click
    public void onBackBtnClicked(View view) {
        // Start MainActivity to go back to the album list
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    // Method for the 'Submit' button click (Update button)
    public void onUpdateBtnClicked(View view) {
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
                album.getMessage(),
                album.getImageResource()
        );

        // Check if any of the fields are empty
        if (Objects.equals(updatedAlbum.getArtistName(), "") ||
                Objects.equals(updatedAlbum.getTitle(), "") ||
                Objects.equals(updatedAlbum.getGenre(), "") ||
                updatedAlbum.getPrice() == 0) {
            // Show a Toast message if any of the fields are empty
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            // If fields are valid, update the album using the ViewModel
            viewModel.updateAlbum(id, updatedAlbum);

            // Start MainActivity after updating the album
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }

    // Method for the 'Delete' button click
    public void onDeleteBtnClicked(View view) {
        // Delete the album using the ViewModel
        viewModel.deleteAlbum(album.getAlbumId());

        // Start MainActivity after deletion to refresh the album list
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
