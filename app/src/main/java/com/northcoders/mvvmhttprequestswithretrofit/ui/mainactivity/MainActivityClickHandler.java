package com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum.AddAlbumActivity;
import com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum.UpdateAlbumActivity;

public class MainActivityClickHandler {

    private Context context;

    // Constructor to initialize the context
    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    // Method to handle the FAB click and navigate to AddNewAlbumActivity
    public void onFABClicked(View view) {
        // Create an intent to open AddAlbumActivity
        Intent intent = new Intent(view.getContext(), AddAlbumActivity.class);
        // Start the activity
        context.startActivity(intent);
    }

    // Method to navigate to UpdateAlbumActivity
    public void onAlbumClicked(View view, int albumId) {
        // Get the context from the view
        Context context = view.getContext();
        // Create an intent to open UpdateAlbumActivity
        Intent intent = new Intent(context, UpdateAlbumActivity.class);
        // Optionally, you can pass data (such as albumId) to the UpdateAlbumActivity
        intent.putExtra("ALBUM_ID", albumId); // Pass album ID to update the album
        // Start the activity
        context.startActivity(intent);
    }
}
