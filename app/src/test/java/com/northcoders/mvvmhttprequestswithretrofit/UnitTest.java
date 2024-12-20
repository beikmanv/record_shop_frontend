package com.northcoders.mvvmhttprequestswithretrofit;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.northcoders.mvvmhttprequestswithretrofit.model.Album;
import com.northcoders.mvvmhttprequestswithretrofit.model.Artist;

public class UnitTest {
    @Test
    public void testAlbumSerialization() {
        // Arrange
        Artist artist = new Artist(0L, "POPPLER");
        Album album = new Album(
                0,
                1,
                artist,
                "SAMPLE",
                "RELIGIOUS",
                10,
                10,
                10.0,
                null,
                null,
                null,
                null
        );

        // Act
        Gson gson = new Gson();
        String json = gson.toJson(album);

        // Expected JSON format for comparison
        String expectedJson = "{\"albumId\":0,\"artistId\":1,\"artist\":{\"artistId\":0,\"artistName\":\"POPPLER\"},"
                + "\"title\":\"SAMPLE\",\"genre\":\"RELIGIOUS\",\"releaseYear\":10,\"stock\":10,\"price\":10.0}";

        // Assert
        assertEquals(expectedJson, json);
    }
}
