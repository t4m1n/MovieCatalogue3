package com.fiqartamin.moviecatalogue3.api;

import com.fiqartamin.moviecatalogue3.Model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JSONResponse {
    @SerializedName("results")
    @Expose
    private ArrayList<Movie> movies = null;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies (ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
