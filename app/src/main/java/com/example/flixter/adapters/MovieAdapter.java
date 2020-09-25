package com.example.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import java.util.List;

//ViewHolder is a parameter of Recycler view
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    //viewholder is a representation of the row
     TextView tvOverview;
     TextView tvTittle;
     ImageView ivPoster;

     Context context;//where adapter is being constructed from
     List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    //INFLATE A VIEW FROM XML AND RETURNS THE VIEWHOLDER
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View MovieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(MovieView) ;
    }
     //populate data through the view/item into the viewholder
    //Take data at a position and put it in a holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get movie passed in the position
        Movie movie = movies.get(position);
        holder.bind(movie);
        //bind movie data into the ViewHolder

    }
// Return total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            tvTittle = itemView.findViewById(R.id.tvTittle);
            ivPoster = itemView.findViewById(R.id.ivPoster);

        }

        public void bind(Movie movie) {
            tvTittle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //IF LANDSCAPE
              //image url becomes backdrop
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl =  movie.getbackdropPath();
            }
            else {
                imageUrl = movie.getPosterpath();
            }
            //else poster
            Glide.with(context).load(imageUrl).into(ivPoster);

        }
    }{

    }
}
