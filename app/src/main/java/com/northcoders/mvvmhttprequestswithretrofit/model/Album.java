package com.northcoders.mvvmhttprequestswithretrofit.model;

import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import java.nio.file.Path;
import java.util.Locale;

public class Album extends BaseObservable {

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

    private int imageResource;

    public Album(int albumId, int artistId, String artistName, String title, String genre, int releaseYear,
                 int stock, double price, String createdAt, String updatedAt, String message, int imageResource) {
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
        this.imageResource = imageResource;
    }

    public Album() {}

    // Getters and setters
    @Bindable
    public int getAlbumId() { return albumId; }
    public void setAlbumId(int albumId) { this.albumId = albumId; }
    @Bindable
    public int getArtistId() { return artistId; }
    public void setArtistId(int artistId) { this.artistId = artistId; }
    @Bindable
    public String getArtistName() { return artistName; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
    @Bindable
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    @Bindable
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    @Bindable
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    @Bindable
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    @Bindable
    public double getPrice() { return price; }
    @BindingAdapter("android:text")
    public static void setPrice(TextView textView, double price) {
        String formattedPrice = String.format(Locale.getDefault(), "£%.2f", price);  // Format price as currency
        textView.setText(formattedPrice);
    }
    @Bindable
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    @Bindable
    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }
    @Bindable
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Bindable
    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
