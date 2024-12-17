package com.northcoders.mvvmhttprequestswithretrofit.model;

import android.app.Application;
import android.util.Log;

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

    public MutableLiveData<List<Album>> getMutableLiveData() {
        // Make the API call to fetch albums
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
}

