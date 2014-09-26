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

import Adapters.HourlyInfo;
import Adapters.ListAdapter;

public class Hourly extends ListFragment {

    private static String mCityName;
    public static final String TAG = "Hourly Fragment";
    private ArrayList<HourlyInfo> mWeatherInfo = new ArrayList<HourlyInfo>();

    public static Hourly newInstance(String _cityName){

        mCityName = _cityName;

        return new Hourly();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.hourly_fragment, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        try {

            String hourlyURL = "http://api.wunderground.com/api/953f79906e09fdb6/hourly/q/CA/";
            URL getHourlyInfo = new URL(hourlyURL + mCityName + ".json");
            new GetWeatherInformation().execute(getHourlyInfo);

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
                weatherArray = weatherData.getJSONArray("hourly_forecast");

                for (int i = 0; i < weatherArray.length(); i++){

                    collectionInfo = weatherArray.getJSONObject(i);

                    mWeatherInfo.add(new HourlyInfo(collectionInfo));

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

            ListAdapter adapter = new ListAdapter(getActivity().getApplicationContext(), mWeatherInfo);
            setListAdapter(adapter);

        }

    }
}
