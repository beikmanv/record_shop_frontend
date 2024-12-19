package com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.model.AlbumRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private final AlbumRepository albumRepository;

    // Constructor for the ViewModel
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        // Initialize the AlbumRepository with the application context
        albumRepository = new AlbumRepository(application);
    }

    // Getter method to access the LiveData of Albums
    public LiveData<List<Album>> getAlbums() {
        return albumRepository.getMutableLiveData();
    }

    // Method to add a new album
    public void addAlbum(Album album) {
        albumRepository.addAlbum(album); // Add the album to the repository
    }

    // Method to update an album
    public void updateAlbum(int id, Album album) {
        albumRepository.updateAlbum(id, album);
    }

    // Method to delete an album
    public void deleteAlbum(int id) {
        albumRepository.deleteAlbum(id);
    }

}

