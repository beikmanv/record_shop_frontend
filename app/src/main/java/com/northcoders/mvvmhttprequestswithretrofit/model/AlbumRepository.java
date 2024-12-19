package com.northcoders.mvvmhttprequestswithretrofit.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.northcoders.mvvmhttprequestswithretrofit.service.AlbumApiService;
import com.northcoders.mvvmhttprequestswithretrofit.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumRepository {

    private final MutableLiveData<List<Album>> mutableLiveData = new MutableLiveData<>();
    private final Application application;

    // Constructor to accept the Application object
    public AlbumRepository(Application application) {
        this.application = application;
    }

    // Fetch the list of albums
    public MutableLiveData<List<Album>> getMutableLiveData() {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<List<Album>> call = albumApiService.getAllAlbums();

        // Make the asynchronous network call
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.body() != null) {
                    List<Album> albums = response.body();
                    // Log artist names in the response
                    for (Album album : albums) {
                        Log.d("AlbumInfo", "Artist Name: " + album.getArtistName()); // Log artist name
                    }
                    // Update the LiveData with the fetched albums
                    mutableLiveData.setValue(albums);
                } else {
                    Log.e("AlbumInfo", "No data received");
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                // Handle failure case
                Log.e("AlbumInfo", "API call failed: " + t.getMessage());
            }
        });

        // Return LiveData to be observed in the UI
        return mutableLiveData;
    }

    // Add a new album to the database (POST request)
    public void addAlbum(Album album) {
        AlbumApiService albumApiService = RetrofitInstance.getService(); // Get the AlbumApiService instance
        Call<Album> call = albumApiService.addAlbum(album); // Create a Call object for the POST request

        call.enqueue(new Callback<Album>() { // Enqueue the call for asynchronous execution
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if (response.isSuccessful()) {
                    // Successfully added the album
                    Toast.makeText(application.getApplicationContext(), "Album added to database", Toast.LENGTH_SHORT).show();

                    // Optionally, update the LiveData with the new list of albums
                    // You could fetch the updated list again (or add the new album to the existing list)
                    fetchAlbums();  // This fetches the list again
                } else {
                    Log.e("POST onResponse", "Error adding album: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Unable to add album to database", Toast.LENGTH_SHORT).show();
                Log.e("POST onFailure", t.getMessage());
            }
        });
    }

    // Optionally, you could fetch the albums again after adding a new one
    private void fetchAlbums() {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<List<Album>> call = albumApiService.getAllAlbums();
        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.body() != null) {
                    List<Album> albums = response.body();
                    // Update the LiveData with the fetched albums
                    mutableLiveData.setValue(albums);
                } else {
                    Log.e("AlbumInfo", "Failed to fetch albums after adding new one");
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Log.e("AlbumInfo", "Failed to fetch albums: " + t.getMessage());
            }
        });
    }
}
