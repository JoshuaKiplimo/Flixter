package com.example.flixter.models;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class Movie {

    String backdrop_path;
    String posterpath;
    String overview;
    String title;

    public Movie(JSONObject jsonObject){
        try {
            backdrop_path = jsonObject.getString("backdrop_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            posterpath = jsonObject.getString("poster_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            title = jsonObject.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            overview = jsonObject.getString("overview");
        } catch (JSONException e) {
            e.printStackTrace();
        }




    };
    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();

        for (int i = 0; i < movieJsonArray.length(); i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i))); //Pass it into movie class that will extract data we need;

        }
        return movies; //List containing movie objects
    }

    //To get data out of the movie objects we created

    public String getPosterpath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterpath);
    }
    public String getbackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdrop_path);
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }
}
