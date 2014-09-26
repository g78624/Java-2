//Kyle Kauck

package Adapters;

import android.util.Log;

import org.json.JSONObject;

public class WeeklyInfo {

    final String TAG = "Hourly Info";

    private String mTime;
    private String mCondition;

    public WeeklyInfo (JSONObject weeklyData){

        try{

            mTime = weeklyData.getString("title");
            mCondition = weeklyData.getString("fcttext");

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

}
