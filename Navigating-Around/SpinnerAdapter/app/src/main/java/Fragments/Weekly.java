//Kyle Kauck

package Fragments;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kyle.spinneradapter.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import Adapters.WeeklyAdapter;
import Adapters.WeeklyInfo;

public class Weekly extends ListFragment {

    private static String mCityName;
    public static final String TAG = "Weekly Fragment";
    public ArrayList<WeeklyInfo> mWeeklyArray = new ArrayList<WeeklyInfo>();

    public static Weekly newInstance(String _cityName){

        mCityName = _cityName;

        return new Weekly();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.weekly_fragment, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        try {

            String weeklyURL = "http://api.wunderground.com/api/953f79906e09fdb6/forecast10day/q/CA/";
            URL getWeeklyInfo = new URL(weeklyURL + mCityName + ".json");
            new GetWeatherInformation().execute(getWeeklyInfo);

        } catch (Exception e){

            Log.e(TAG, "Something went bad");

        }

    }

    private class GetWeatherInformation extends AsyncTask<URL, Integer, JSONObject> {

        @Override
        protected JSONObject doInBackground(URL... params) {

            String currentWeather = "";

            for (URL getInfo : params) {

                try {

                    URLConnection connect = getInfo.openConnection();
                    currentWeather = IOUtils.toString(connect.getInputStream());
                    break;

                } catch (Exception e) {

                    Log.e(TAG, "Something Went Wrong");

                }


            }

            JSONObject weatherData;
            JSONObject collectionInfo = new JSONObject();
            JSONArray weatherArray = new JSONArray();

            try {

                weatherData = new JSONObject(currentWeather);
                weatherArray = weatherData.getJSONObject("forecast").getJSONObject("txt_forecast").getJSONArray("forecastday");

                for (int i = 0; i < weatherArray.length(); i++){

                    collectionInfo = weatherArray.getJSONObject(i);

                    mWeeklyArray.add(new WeeklyInfo(collectionInfo));

                }

                Log.i(TAG, "Hello");

            } catch (Exception e) {

                Log.e(TAG, "Cannot Convert");
                weatherData = null;

            }

            return weatherData;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {

            WeeklyAdapter adapter = new WeeklyAdapter(getActivity().getApplicationContext(), mWeeklyArray);
            setListAdapter(adapter);

            //ListAdapter adapter = new ListAdapter(getActivity().getApplicationContext(), mWeeklyArray);
            //setListAdapter(adapter);

        }

    }

}
