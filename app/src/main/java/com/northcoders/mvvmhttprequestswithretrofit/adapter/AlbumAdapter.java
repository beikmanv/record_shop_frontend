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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> albumList;
    private List<Integer> availableImages;  // To hold available images

    public AlbumAdapter(List<Album> albumList) {
        this.albumList = albumList;
        this.availableImages = new ArrayList<>();
        // Initialize the available image list
        Collections.addAll(availableImages,
                R.drawable.album1, R.drawable.album2, R.drawable.album3,
                R.drawable.album4, R.drawable.album5, R.drawable.album6,
                R.drawable.album7, R.drawable.album8
        );
        Collections.shuffle(availableImages);  // Shuffle to randomize image order
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumTitle.setText(album.getTitle());
        holder.artistName.setText(album.getArtistName());
        holder.genreReleaseYear.setText(album.getGenre() + " | " + album.getReleaseYear());
        holder.price.setText("£" + album.getPrice());

        // Ensure each image is used randomly by either removing it from available list or reshuffling the list
        if (!availableImages.isEmpty()) {
            // Get a random image from the available images list
            int randomImage = availableImages.get(0);  // Get the first image from the list
            availableImages.remove(0);  // Remove the used image
            holder.albumImage.setImageResource(randomImage);  // Set the image to the ImageView
        } else {
            // If the list of available images is empty, refill the list with images and shuffle again
            Collections.addAll(availableImages,
                    R.drawable.album1, R.drawable.album2, R.drawable.album3,
                    R.drawable.album4, R.drawable.album5, R.drawable.album6,
                    R.drawable.album7, R.drawable.album8
            );
            Collections.shuffle(availableImages);  // Shuffle the images again to randomize
            // After refill, pick an image from the reshuffled list
            int randomImage = availableImages.get(0);
            availableImages.remove(0);  // Remove it from the available list
            holder.albumImage.setImageResource(randomImage);  // Set the image to the ImageView
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    // Method to update the album list
    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

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
