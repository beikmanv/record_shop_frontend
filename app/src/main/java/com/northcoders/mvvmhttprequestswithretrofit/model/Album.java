package com.northcoders.mvvmhttprequestswithretrofit.model;

import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.northcoders.mvvmhttprequestswithretrofit.BR;

import java.util.Locale;

public class Album extends BaseObservable {

    private int albumId;
    private int artistId;
    private Artist artist;
    private String artistName; // For GET response only
    private String title;
    private String genre;
    private int releaseYear;
    private int stock;
    private double price;
    private String createdAt;
    private String updatedAt;
    private String message;

    private String imageResource;

    // Main Constructor
    public Album(int albumId, int artistId, Artist artist, String artistName, String title, String genre, int releaseYear,
                 int stock, double price, String createdAt, String updatedAt, String message, String imageResource) {
        this.albumId = albumId;
        this.artistId = artistId;
        this.artist = artist;
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

    public Album() {
        this.artist = new Artist(0, ""); // Provide default values
    }

    // For GET response only
    @Bindable
    public String getArtistName() {
        return artistName != null ? artistName : (artist != null ? artist.getArtistName() : null);
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
        notifyPropertyChanged(BR.artistName);
    }

    // Getters and setters with @Bindable for data binding
    @Bindable
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
        notifyPropertyChanged(BR.albumId);
    }

    @Bindable
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
        notifyPropertyChanged(BR.artistId);
    }

    @Bindable
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
        notifyPropertyChanged(BR.artist);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    @Bindable
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        notifyPropertyChanged(BR.releaseYear);
    }

    @Bindable
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        notifyPropertyChanged(BR.stock);
    }

    @Bindable
    public double getPrice() {
        return price;
    }

    @BindingAdapter("android:text")
    public static void setPrice(TextView textView, double price) {
        String formattedPrice = String.format(Locale.getDefault(), "£%.2f", price); // Format price as pounds
        textView.setText(formattedPrice);
    }

    public void setPrice(double price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        notifyPropertyChanged(BR.createdAt);
    }

    @Bindable
    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        notifyPropertyChanged(BR.updatedAt);
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
        notifyPropertyChanged(BR.imageResource);
    }
}
