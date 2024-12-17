package com.northcoders.mvvmhttprequestswithretrofit.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private List<Album> albumList; // The data to display (a list of Album objects)
    private Context context; // Context to access resources
    private MediaPlayer mediaPlayer; // MediaPlayer instance
    private boolean isPlaying = false; // Flag to check if music is playing
    private String musicLink = "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3"; // Test music link

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
    }

    public AlbumAdapter(List<Album> albumList) {
        this.albumList = albumList;
        mediaPlayer = new MediaPlayer(); // Initialize MediaPlayer here
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

        // Set the play button image from the URL
        Glide.with(holder.playButton.getContext())
                .load("https://upload.wikimedia.org/wikipedia/commons/7/70/Example.png")  // Link to the play button icon
                .into(holder.playButton);

        // Handle the play button click
        holder.playButton.setOnClickListener(v -> {
            // Toggle between play and pause
            if (isPlaying) {
                pauseMusic(); // Pause music if it's already playing
                holder.playButton.setImageResource(R.drawable.ic_play_button); // Update play button image
            } else {
                playMusic(v.getContext(), album); // Play music if it's paused
                holder.playButton.setImageResource(R.drawable.ic_pause_button); // Update play button image to pause
            }
            isPlaying = !isPlaying; // Toggle the playing state
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        TextView albumTitle, artistName, genreReleaseYear, price;
        ImageView albumImage, playButton;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            albumTitle = itemView.findViewById(R.id.albumTitle);
            artistName = itemView.findViewById(R.id.artistName);
            genreReleaseYear = itemView.findViewById(R.id.genreReleaseYear);
            price = itemView.findViewById(R.id.price);
            albumImage = itemView.findViewById(R.id.albumImage);
            playButton = itemView.findViewById(R.id.playButton);  // Reference the play button
        }
    }

    // Method to play the music
    public void playMusic(Context context, Album album) {
        try {
            // Set the audio source (streaming from the URL)
            mediaPlayer.setDataSource(musicLink);
            mediaPlayer.prepare(); // Prepare the MediaPlayer
            mediaPlayer.start(); // Start playing the music

            // Get the AudioManager system service to control the volume
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            // Set the volume to 75% of the maximum volume
            int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // Get the max volume
            int desiredVolume = (int) (maxVolume * 0.75);  // 75% of the maximum volume
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, desiredVolume, 0); // Set the volume to the desired level

            // Optionally show a toast message
            Toast.makeText(context, "Playing: " + album.getTitle() + " at 75% volume", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Error playing music", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to pause the music
    private void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause(); // Pause the media player if it's playing
        }
    }
}
