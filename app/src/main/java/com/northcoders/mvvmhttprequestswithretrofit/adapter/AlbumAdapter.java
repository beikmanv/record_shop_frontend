package com.northcoders.mvvmhttprequestswithretrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> albumList; // The data to display (a list of Album objects)
    private Context context; // Context to access resources

    // Map to hold image names and their corresponding resource IDs
    private static final Map<String, Integer> imageResourceMap = new HashMap<>();

    static {
        // Fill the map with the image names and their corresponding drawable resource IDs
        imageResourceMap.put("album1", R.drawable.album1);
        imageResourceMap.put("album2", R.drawable.album2);
        imageResourceMap.put("album3", R.drawable.album3);
        imageResourceMap.put("album4", R.drawable.album4);
        imageResourceMap.put("album5", R.drawable.album5);
        imageResourceMap.put("album6", R.drawable.album6);
        imageResourceMap.put("album7", R.drawable.album7);
        imageResourceMap.put("album8", R.drawable.album8);
        imageResourceMap.put("album9", R.drawable.album9);
        imageResourceMap.put("album10", R.drawable.album10);

        // Add more entries as needed
    }

    public AlbumAdapter(List<Album> albumList) {
        this.albumList = albumList;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for a single row (album_item.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_item, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        // Set text views
        holder.albumTitle.setText(album.getTitle());
        holder.artistName.setText(album.getArtistName());
        holder.genreReleaseYear.setText(album.getGenre() + " | " + album.getReleaseYear());
        holder.price.setText("£" + album.getPrice());

        // Get the image name from the album object
        String imageName = album.getImageResource();

        // Lookup the image resource ID from the map
        Integer imageResId = imageResourceMap.get(imageName);

        if (imageResId != null) {
            // If the image resource exists, set it
            holder.albumImage.setImageResource(imageResId);
        } else {
            // If the image is not found in the map, set a default image
            holder.albumImage.setImageResource(R.drawable.album10);
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
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
