//Kyle Kauck

package com.example.kyle.java2_fundamentals;

import android.util.Log;

import org.json.JSONObject;

public class Movie {

    final String TAG = "Movie Class";

    private String mName;
    private String mRating;
    private Integer mScore;
    private String mScoreName;
    private Integer mYear;

    //This will parse the information out from the retrieved JSON from Rotten Tomatoes
    public Movie (JSONObject movieData){

        try {

            mName = movieData.getString("title");
            mRating = movieData.getString("mpaa_rating");
            mScore = movieData.getJSONObject("ratings").getInt("critics_score");
            mScoreName  = movieData.getJSONObject("ratings").optString("critics_rating", "No Critics Rating");
            mYear = movieData.optInt("year");

        } catch (Exception e){

            Log.e (TAG, "Something went terribly wrong again!");

        }

    }

    public String getName(){

        return mName;

    }

    public void setName(String mName){

        this.mName = mName;

    }

    public String getRating(){

        return mRating;

    }

    public void setRating(String mRating){

        this.mRating = mRating;

    }

    public Integer getScore(){

        return mScore;

    }

    public void setScore(Integer mScore){

        this.mScore = mScore;

    }

    public String getScoreName(){

        return mScoreName;

    }

    public void setScoreName(String mScoreName){

        this.mScoreName = mScoreName;

    }

    public Integer getYear(){

        return mYear;

    }

    public void setYear(Integer mYear){

        this.mYear = mYear;

    }

}
