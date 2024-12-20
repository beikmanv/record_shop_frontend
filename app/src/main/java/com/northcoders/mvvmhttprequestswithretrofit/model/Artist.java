package com.northcoders.mvvmhttprequestswithretrofit.model;

import static android.content.ContentValues.TAG;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Artist implements Parcelable {

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

    // Constructor for Parcel
    protected Artist(Parcel in) {
        artistId = in.readInt();  // Read artistId from Parcel
        artistName = in.readString();  // Read artistName from Parcel

        // Log the created object
        Log.d(TAG, "Creating artist from parcel: " + this.toString());
    }


    @Override
    public int describeContents() {
        return 0; // No special objects to describe
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(artistId);  // Write artistId to Parcel
        dest.writeString(artistName);  // Write artistName to Parcel

        // Log the written object
        Log.d(TAG, "Writing artist to parcel: " + this.toString());
    }

    // Creator to recreate the object from Parcel
    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);  // Recreate Artist object from Parcel
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];  // Create an array of Artist objects
        }
    };

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
