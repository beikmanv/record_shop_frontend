package com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.northcoders.mvvmhttprequestswithretrofit.ui.addalbum.AddAlbumActivity;

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
}
