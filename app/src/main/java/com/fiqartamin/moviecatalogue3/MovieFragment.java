package com.fiqartamin.moviecatalogue3;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fiqartamin.moviecatalogue3.Model.Movie;
import com.fiqartamin.moviecatalogue3.adapter.MovieViewAdapter;
import com.fiqartamin.moviecatalogue3.api.JSONResponse;
import com.fiqartamin.moviecatalogue3.api.RequestInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Movie> movieList;
    private MovieViewAdapter adapter;
    private ProgressBar progressBar;

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "0cbd025093b0e759240fe77ca65ccc99";

    View v;


    public MovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_movie, container, false);

        progressBar = v.findViewById(R.id.progressBar);
        recyclerView = v.findViewById(R.id.movie_rv);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        showLoading(true);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepare();
    }

    private void prepare() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSONMovie(API_KEY, getString(R.string.language_id));
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if (response.isSuccessful()) {
                    showLoading(false);

                    movieList = response.body().getMovies();
                    adapter = new MovieViewAdapter(movieList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Eror", t.getMessage());
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
