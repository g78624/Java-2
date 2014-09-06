package com.example.kyle.java2_fundamentals;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class MovieData implements Serializable {

    private static final long serialVersionUID = -3718973198731323L;
    private final String TAG = "Movie Data";

    private String mName;
    private String mRating;
    private Integer mScore;
    private String mScoreName;
    private Integer mYear;

    public MovieData(){

        mName = "";
        mRating = "";
        mScore = 0;
        mScoreName = "";
        mYear = 0;

    }

    public MovieData (JSONObject tempMovieData){

        try {

            mName = tempMovieData.getString("title");
            mRating = tempMovieData.getString("mpaa_rating");
            mScore = tempMovieData.getJSONObject("ratings").getInt("critics_score");
            mScoreName  = tempMovieData.getJSONObject("ratings").optString("critics_rating", "No Critic Rating");
            mYear = tempMovieData.optInt("year", 0000);

        } catch (Exception e){

            Log.e(TAG, "Something went terribly wrong again!");

        }

    }

    public String getName(){

        return mName;

    }

    public String getRating(){

        return mRating;

    }

    public Integer getScore(){

        return mScore;

    }

    public String getScoreName(){

        return mScoreName;

    }

    public Integer getYear(){

        return mYear;

    }

}
