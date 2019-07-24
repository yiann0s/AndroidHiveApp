package com.example.androidhiveapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhiveapp.R;
import com.example.androidhiveapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private List<Movie> movies;
    private int rowLayout;

    public MovieAdapter(List<Movie> movies, int rowLayout, Context context){
        this.movies = movies;
        this.mContext = context;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        final Movie movie = movies.get(position);

        holder.movieTitle.setText(movie.getTitle());
        holder.releaseDate.setText(movie.getReleaseDate());
        holder.movieDescription.setText(movie.getOverview());
//        holder.rating.setText(movie.getVoteAverage().toString());
        // This is how we use Picasso to load images from the internet.
        Picasso.get()
                .load("http://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                .error(R.color.colorAccent)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView releaseDate;
        TextView movieDescription;
        TextView rating;
        ImageView imageView;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            movieTitle = (TextView)v.findViewById(R.id.title);
            releaseDate = (TextView) v.findViewById(R.id.release);
            movieDescription = (TextView)v.findViewById(R.id.description);
        }

    }
}
