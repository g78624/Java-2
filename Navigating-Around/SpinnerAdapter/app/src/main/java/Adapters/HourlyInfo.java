//Kyle Kauck

package Adapters;

import android.util.Log;

import org.json.JSONObject;

public class HourlyInfo {

    final String TAG = "Hourly Info";

    private String mTime;
    private String mCondition;
    private String mTemp;

    public HourlyInfo (JSONObject hourlyData){

        try{

            mTime = hourlyData.getJSONObject("FCTTIME").getString("pretty");
            mCondition = hourlyData.getString("condition");
            mTemp = hourlyData.getJSONObject("temp").getString("english");

        } catch (Exception e){

            Log.e(TAG, "This is not working");

        }

    }

    public String getTime(){

        return mTime;

    }

    public String getCondition(){

        return mCondition;

    }

    public String getTemp(){

        return mTemp;

    }

}
