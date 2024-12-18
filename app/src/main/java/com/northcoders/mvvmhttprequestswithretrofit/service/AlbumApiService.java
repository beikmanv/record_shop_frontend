package com.northcoders.mvvmhttprequestswithretrofit.service;

import com.northcoders.mvvmhttprequestswithretrofit.model.Album;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AlbumApiService {

    @GET("album")
    Call<List<Album>> getAllAlbums();

    @POST("")
    Call<Album> addAlbum(@Body Album album);

}
