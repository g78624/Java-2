package Adapters;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class WeatherInfo implements Serializable {

    final String TAG = "Weather Info";

    private int mTemp;
    private String mTime;
    private String mHumidity;
    private String mWind;
    private String mDirection;
    private String mCondition;

    public WeatherInfo (JSONObject weatherInfo){

        try {

            mTemp = weatherInfo.getInt("temp_f");
            mTime = weatherInfo.getString("observation_time");
            mHumidity = weatherInfo.getString("relative_humidity");
            mWind = weatherInfo.getString("wind_string");
            mDirection = weatherInfo.getString("wind_dir");
            mCondition = weatherInfo.getString("weather");

        } catch (Exception e){

            Log.e (TAG, "Something went wrong");

        }

    }

    public Integer setTemp(){

        return mTemp;

    }

    public String setTime(){

        return mTime;

    }

    public String setHumidity(){

        return mHumidity;

    }

    public String setWind(){

        return mWind;

    }

    public String setDirection(){

        return mDirection;

    }

    public String setCondition(){

        return mCondition;

    }

}
