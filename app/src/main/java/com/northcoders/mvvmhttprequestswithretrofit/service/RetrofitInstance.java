package com.northcoders.mvvmhttprequestswithretrofit.service;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;

    private final static String BASE_URL = "http://10.0.2.2:8084/api/v1/";

    public static AlbumApiService getService() {

        // Custom OkHttpClient with additional logging interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        // Log the request URL
                        Log.d("Retrofit", "Request URL: " + request.url());

                        // Log the request body if it's not null
                        if (request.body() != null) {
                            Log.d("Retrofit", "Request Body: " + request.body().toString());
                        }

                        return chain.proceed(request);
                    }
                })
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)) // Default logging interceptor
                .build();

        // Check if the Retrofit instance is null, and if so, initialize it
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client) // Use the custom OkHttpClient
                    .build();
        }

        return retrofit.create(AlbumApiService.class);
    }
}
