package com.northcoders.mvvmhttprequestswithretrofit.service;

import com.northcoders.mvvmhttprequestswithretrofit.model.Album;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlbumApiService {

    @GET("album")
    Call<List<Album>> getAllAlbums();

    @POST("album")
    Call<Album> addAlbum(@Body Album album);

    @PUT("album/{id}")
    Call<Album> updateAlbum(
            @Path("id") Long albumId,
            @Body Album album
    );

    @DELETE("album/{id}")
    Call<Void> deleteAlbum(
            @Path("id") Long albumId
    );

}
