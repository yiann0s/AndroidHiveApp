package com.example.androidhiveapp.activities;


import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhiveapp.adapters.MovieAdapter;
import com.example.androidhiveapp.R;
import com.example.androidhiveapp.Utils.RetroService;
import com.example.androidhiveapp.model.Movie;
import com.example.androidhiveapp.model.MovieResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopRatedMoviesActivity extends AppCompatActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    String API_BASE_URL = "https://api.themoviedb.org/3/"; // you can grab this url
                                                            // https://api.themoviedb.org/3/movie/top_rated?api_key=efbdebf1b30ffab728c49495748e9dfa
                                                            // and check its json response
    private final static String API_KEY = "efbdebf1b30ffab728c49495748e9dfa";  //unique api key per user

    private static final String TAG = "TEST.Movies"; // for logging

    List<Movie> movieArrayList = null;

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );

    Retrofit retrofit =
            builder
                    .client(
                            httpClient.build()
                    )
                    .build();

    retrofit2.Call<MovieResponse> movieCall;

    private RetroService retroService;

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_movies);

        recyclerView = findViewById(R.id.recyclerList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        movieArrayList = new ArrayList<Movie>();


        pDialog = new ProgressDialog(TopRatedMoviesActivity.this);
        pDialog.setMessage("Loading products. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        retroService = retrofit.create(RetroService.class);
        movieCall = retroService.getTopRatedMovies(API_KEY);
        movieCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "success");
                    movieArrayList = response.body().getResults();
                    recyclerView.setAdapter(new MovieAdapter(movieArrayList, R.layout.movie_row,
                            TopRatedMoviesActivity.this));
                } else {
                    Log.d(TAG, "not successful");
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure - " + t.getLocalizedMessage());
                pDialog.dismiss();
            }
        });

    }
}