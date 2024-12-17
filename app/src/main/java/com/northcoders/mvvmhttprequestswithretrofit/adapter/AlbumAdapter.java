package com.northcoders.mvvmhttprequestswithretrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// The AlbumAdapter is a bridge between the data (a list of albums) and the UI (the RecyclerView)
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> albumList; // The data to display (a list of Album objects)
    private final List<Integer> availableImages;  // List of random images for albums
    private int imageIndex = 0;  // Keeps track of which image to assign next

    public AlbumAdapter(List<Album> albumList) {
        this.albumList = albumList;
        this.availableImages = new ArrayList<>();
        // Initialize the available image list and shuffle it once
        Collections.addAll(availableImages,
                R.drawable.album1, R.drawable.album2, R.drawable.album3,
                R.drawable.album4, R.drawable.album5, R.drawable.album6,
                R.drawable.album7, R.drawable.album8
        );
        Collections.shuffle(availableImages);  // Shuffle once during initialization
    }

    @NonNull
    @Override
    // Instead of searching for the views every time (using findViewById), the ViewHolder holds references to them
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single row (album_item.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new AlbumViewHolder(view); // Return a new ViewHolder with this layout
    }

    @Override
    // This is where the actual data (album title, artist, etc.) is placed into the UI for each row
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        // Set text views
        holder.albumTitle.setText(album.getTitle());
        holder.artistName.setText(album.getArtistName());
        holder.genreReleaseYear.setText(album.getGenre() + " | " + album.getReleaseYear());
        holder.price.setText("£" + album.getPrice());
        // Set album image, ensure it's a valid index before using the image list
        if (imageIndex >= availableImages.size()) {
            // Refill and shuffle images once the list is exhausted
            Collections.shuffle(availableImages);  // Shuffle images
            imageIndex = 0;  // Reset index
        }
        // Set the image for the album
        holder.albumImage.setImageResource(availableImages.get(imageIndex));
        imageIndex++;  // Increment the index for the next image
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    // The ViewHolder acts as a shortcut for finding and holding all the views (like TextView, ImageView)
    // in each item row of the RecyclerView. It improves performance by avoiding repeated calls to findViewById().
    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumTitle, artistName, genreReleaseYear, price;
        ImageView albumImage;
        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumTitle = itemView.findViewById(R.id.albumTitle);
            artistName = itemView.findViewById(R.id.artistName);
            genreReleaseYear = itemView.findViewById(R.id.genreReleaseYear);
            price = itemView.findViewById(R.id.price);
            albumImage = itemView.findViewById(R.id.albumImage);
        }
    }
}
