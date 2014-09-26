package Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.kyle.spinneradapter.R;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import Adapters.WeatherInfo;

public class MainFragment extends Fragment {

    private String mCity;
    private static String mSavedCity;
    public static final String TAG = "Main Fragment";
    private TextView mCityName;
    private ArrayList<WeatherInfo> mWeatherInfo = new ArrayList<WeatherInfo>();
    private cityName mReturnCity;

    public static MainFragment newInstance(String _cityName){

        mSavedCity = _cityName;

        return new MainFragment();

    }

    public interface cityName{

        public void cityNameReturn(String city);

    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        if (activity instanceof cityName){

            mReturnCity = (cityName) activity;

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.main_fragment, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        View view =  getView();

        if (mSavedCity != null){

            try {

                mCity = mSavedCity.replace("_", " ");
                String currentURL = "http://api.wunderground.com/api/953f79906e09fdb6/conditions/q/CA/";
                URL getCurrentInfo = new URL(currentURL + mSavedCity + ".json");
                new GetWeatherInformation().execute(getCurrentInfo);

            } catch (Exception e){

                Log.e (TAG, "This went poorly!");

            }
        }

        mCityName = (TextView) view.findViewById(R.id.citySearch);

        Button search = (Button) view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                InputMethodManager hideKeyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                hideKeyboard.hideSoftInputFromWindow(mCityName.getWindowToken(), 0);

                String cityName = mCityName.getText().toString().replace(" ", "_");
                mCity = mCityName.getText().toString();

                mReturnCity.cityNameReturn(cityName);

                try {

                    String currentURL = "http://api.wunderground.com/api/953f79906e09fdb6/conditions/q/CA/";
                    URL getCurrentInfo = new URL (currentURL + cityName + ".json");
                    new GetWeatherInformation().execute(getCurrentInfo);

                    mCityName.setText("");


                } catch (Exception e){

                    Log.e(TAG, "Invalid Information");

                }

            }

        });


    }

    public void updateDisplay(WeatherInfo newWeather) {

        View view =  getView();

        TextView cityName = (TextView) view.findViewById(R.id.cityName);
        TextView time = (TextView) view.findViewById(R.id.lastUpdated);
        TextView temp = (TextView) view.findViewById(R.id.hourlyTemp);
        TextView condition = (TextView) view.findViewById(R.id.hourlyCondition);
        TextView humidity = (TextView) view.findViewById(R.id.humidity);
        TextView wind = (TextView) view.findViewById(R.id.windSpeed);
        TextView direction = (TextView) view.findViewById(R.id.direction);

        cityName.setText(mCity);
        time.setText(newWeather.setTime());
        temp.setText("Temperature: " + newWeather.setTemp().toString());
        condition.setText("Current Conditions: " + newWeather.setCondition());
        humidity.setText("Humidity: " + newWeather.setHumidity());
        wind.setText("Wind Strength: " + newWeather.setWind());
        direction.setText("Wind Direction: " + newWeather.setDirection());

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

            try {

                weatherData = new JSONObject(currentWeather).getJSONObject("current_observation");

                mWeatherInfo.add(new WeatherInfo(weatherData));

                Log.i(TAG, "Hello");

            } catch (Exception e) {

                Log.e(TAG, "Cannot Convert");
                weatherData = null;

            }

            return weatherData;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {

            WeatherInfo newWeather = new WeatherInfo(jsonObject);

            updateDisplay(newWeather);

        }

    }

}
