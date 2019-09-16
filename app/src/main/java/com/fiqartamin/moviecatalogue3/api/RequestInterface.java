package com.fiqartamin.moviecatalogue3.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestInterface {

    @GET ("discover/movie")
    Call<JSONResponse> getJSONMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET ("discover/tv")
    Call<JSONResponse> getJSONTv(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
