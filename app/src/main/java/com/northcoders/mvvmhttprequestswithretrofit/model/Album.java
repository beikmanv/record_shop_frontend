package com.northcoders.mvvmhttprequestswithretrofit.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.northcoders.mvvmhttprequestswithretrofit.BR;

import java.util.Locale;

public class Album extends BaseObservable implements Parcelable {

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

    // Default constructor for Parcelable
    public Album() {
        this.artist = new Artist(0, ""); // Provide default values
    }

    // Parcelable constructor
    protected Album(Parcel in) {
        albumId = in.readInt();
        artistId = in.readInt();
        artist = in.readParcelable(Artist.class.getClassLoader());
        artistName = in.readString();
        title = in.readString();
        genre = in.readString();
        releaseYear = in.readInt();
        stock = in.readInt();
        price = in.readDouble();
        createdAt = in.readString();
        updatedAt = in.readString();
        message = in.readString();
        imageResource = in.readString();
    }

    @Override
    public int describeContents() {
        return 0; // No special objects, so return 0
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(albumId);
        dest.writeInt(artistId);
        dest.writeParcelable(artist, flags); // Write Artist object (which should also implement Parcelable)
        dest.writeString(artistName);
        dest.writeString(title);
        dest.writeString(genre);
        dest.writeInt(releaseYear);
        dest.writeInt(stock);
        dest.writeDouble(price);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(message);
        dest.writeString(imageResource);
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in); // Create a new Album from the Parcel
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size]; // Create a new array of Album objects
        }
    };

    // Getters and setters for the fields, using @Bindable for data binding
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
    public String getArtistName() {
        return artistName != null ? artistName : (artist != null ? artist.getArtistName() : null);
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
        notifyPropertyChanged(BR.artistName);
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
