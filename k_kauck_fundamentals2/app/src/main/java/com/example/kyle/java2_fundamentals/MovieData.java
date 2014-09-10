//Kyle Kauck

package com.example.kyle.java2_fundamentals;

import org.json.JSONObject;

import java.io.Serializable;

public class MovieData implements Serializable {

    private static final long serialVersionUID = -3718973198731323L;

    private String mName;
    private String mRating;
    private Integer mScore;
    private String mScoreName;
    private Integer mYear;

    //This will parse the saved JSON Data from the File, And also is used to save out the Web API Information to the Save File
    public MovieData (JSONObject tempMovieData){

        try {

            mName = tempMovieData.getString("title");
            mRating = tempMovieData.getString("mpaa_rating");
            mScore = tempMovieData.getJSONObject("ratings").getInt("critics_score");
            mScoreName  = tempMovieData.getJSONObject("ratings").optString("critics_rating", "No Critic Rating");
            mYear = tempMovieData.optInt("year", 0);

        } catch (Exception e){

            e.printStackTrace();

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
