package com.example.flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.DetailActivity;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import static com.example.flixter.R.layout.item_movie;

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
        View MovieView = LayoutInflater.from(context).inflate(item_movie, parent, false);
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

         RelativeLayout container;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            tvTittle = itemView.findViewById(R.id.tvTittle);
            ivPoster = itemView.findViewById(R.id.ivPoster);

        }

        public void bind(final Movie movie) {
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


            //Create a listener to know which movie was clicked - on the whole container=- we got id of the container above
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, DetailActivity.class);//pass the new activity we are creating into intent and start the activity

                    i.putExtra("movie", Parcels.wrap(movie));


                    context.startActivity(i);

                }
            });

            //Action taken to navigate to new activity




        }
    }{

    }
}
