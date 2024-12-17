package com.northcoders.mvvmhttprequestswithretrofit.model;

import java.nio.file.Path;

public class Album {

    private int albumId;
    private int artistId;
    private String artistName;
    private String title;
    private String genre;
    private int releaseYear;
    private int stock;
    private double price;
    private String createdAt;
    private String updatedAt;
    private String message;

    public Album(int albumId, int artistId, String artistName, String title, String genre, int releaseYear,
                 int stock, double price, String createdAt, String updatedAt, String message) {
        this.albumId = albumId;
        this.artistId = artistId;
        this.artistName = artistName;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.stock = stock;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.message = message;
    }

    public Album() {}

    // Getters and setters
    public int getAlbumId() { return albumId; }
    public void setAlbumId(int albumId) { this.albumId = albumId; }
    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

}
