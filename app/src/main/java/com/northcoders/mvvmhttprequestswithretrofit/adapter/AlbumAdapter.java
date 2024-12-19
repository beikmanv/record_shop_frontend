package com.northcoders.mvvmhttprequestswithretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.northcoders.mvvmhttprequestswithretrofit.R;
import com.northcoders.mvvmhttprequestswithretrofit.databinding.AlbumItemBinding;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.ui.mainactivity.RecyclerViewInterface;
import com.northcoders.mvvmhttprequestswithretrofit.ui.updatealbum.UpdateAlbumActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private ArrayList<Album> albumList;
    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
//    private MediaPlayer mediaPlayer;
    private static final Map<String, Integer> imageResourceMap = new HashMap<>();

    public AlbumAdapter(Context context, ArrayList<Album> albumList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.albumList = albumList;
        this.recyclerViewInterface = recyclerViewInterface;
//        this.mediaPlayer = new MediaPlayer();
    }

    public void updateAlbums(ArrayList<Album> albums) {
        this.albumList = albums;
        notifyDataSetChanged();
    }

    static {
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

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AlbumItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.album_item,
                parent,
                false
        );
        return new AlbumViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumItemBinding.setAlbum(album); // Bind data
        // Set album image using Glide
        String imageName = album.getImageResource();
        Integer imageResId = imageResourceMap.get(imageName);
        if (imageResId != null) {
            Glide.with(holder.itemView.getContext())
                    .load(imageResId)
                    .into(holder.albumItemBinding.albumImage);
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.album10)
                    .into(holder.albumItemBinding.albumImage);
        }
        // Set up click listener for the item
        holder.itemView.setOnClickListener(v -> {
            if (context != null) {
                Intent intent = new Intent(context, UpdateAlbumActivity.class);
                intent.putExtra("albumId", album.getAlbumId()); // Pass album data
                intent.putExtra("albumTitle", album.getTitle());
                intent.putExtra("albumArtist", album.getArtistName());
                context.startActivity(intent);
            } else {
                Toast.makeText(v.getContext(), "Context is null!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        AlbumItemBinding albumItemBinding;

        public AlbumViewHolder(AlbumItemBinding albumItemBinding, RecyclerViewInterface recyclerViewInterface) {
            super(albumItemBinding.getRoot());
            this.albumItemBinding = albumItemBinding;
        }
    }
}
