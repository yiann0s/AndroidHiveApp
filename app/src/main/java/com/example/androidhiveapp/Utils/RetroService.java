package com.example.androidhiveapp.Utils;

import com.example.androidhiveapp.model.MovieResponse;

import retrofit2.Call;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroService {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);


}
