package com.northcoders.mvvmhttprequestswithretrofit.model;

public class Artist {
    private int artistId;
    private String artistName;

    // Default constructor
    public Artist() {
        this.artistId = 0;  // Default ID
        this.artistName = "";  // Default name
    }

    // Constructor
    public Artist(int artistId, String artistName) {
        this.artistId = artistId;
        this.artistName = artistName;
    }

    // Getters and Setters
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}

