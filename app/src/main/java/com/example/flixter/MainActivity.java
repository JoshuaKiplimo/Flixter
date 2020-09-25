package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixter.adapters.MovieAdapter;
import com.example.flixter.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;


public class MainActivity extends AppCompatActivity {
    List<Movie> movies;
    public static final String Now_Playing_Url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = new ArrayList<>();
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        //create adapter
        //(pass in the adapter that we created before)
        final MovieAdapter movieAdapter= new MovieAdapter (this, movies);
        //set adapter on recycler view
        rvMovies.setAdapter(movieAdapter);
        //set layout manager on recycler view so that recycler view can know how to layout diffrent views on the screen
        rvMovies.setLayoutManager(new LinearLayoutManager(this));






        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Now_Playing_Url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                //Log.d(TAG, "onSuccess:");
                JSONObject jsonObject = json.jsonObject;

                try {
                    //Might be some issues with getting the key "results"
                    JSONArray results=  jsonObject.getJSONArray("results");
                    Log.i(TAG, "message" + results.toString() );
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();//to notify movie adapter if movie has changed
                    Log.i(TAG, "movies" + movies.size());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}