//Kyle Kauck

package com.example.kyle.java2_fundamentals;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import fragments.DetailsFragment;
import fragments.MasterFragment;


public class MainActivity extends Activity implements MasterFragment.apiSearchWord, MasterFragment.dataDetails {

    private ArrayList<Movie> mMovieArrray = new ArrayList<Movie>();
    private static final String FILENAME = "movieData.txt";
    private ArrayList<MovieData> mSaveMovie = new ArrayList<MovieData>();
    private JSONArray mJSONMovieArray;
    MovieData mMovieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieData = null;

        if(savedInstanceState == null){

            //Will replace my master container with the master_fragment xml information
            MasterFragment frag = MasterFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.master_container, frag, MasterFragment.TAG).commit();

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent newData){

        super.onActivityResult(requestCode, resultCode, newData);

        if (requestCode == 10010){

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean pref = preferences.getBoolean("PREF_ONLINE", true);

            Toast.makeText(this, "This Is Working Fine" + pref, Toast.LENGTH_SHORT).show();

            if (!pref){

                Toast.makeText(this, "This Means You Are Online!", Toast.LENGTH_SHORT).show();
                MasterFragment frag = (MasterFragment) getFragmentManager().findFragmentById(R.id.master_container);
                frag.getFreshData();


            } else {

                Toast.makeText(this, "This Means You Are Offline!", Toast.LENGTH_SHORT).show();
                noNetworkData();


            }

        }

    }

    //Simple check to see which preference has been selected by the user
    public boolean checkForPref(){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean pref = preferences.getBoolean("PREF_ONLINE", true);

        if (pref == true){

            return true;

        } else {

            return false;

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();
        if (id == R.id.action_settings){

            Intent newIntent = new Intent();
            newIntent.setClass(this, PreferencesActivity.class);
            startActivityForResult(newIntent, 10010);

            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        ActionBar getActionBar  = getActionBar();
        assert getActionBar != null;
        getActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F87217")));

        return true;
    }

    public boolean networkConnection(){

        ConnectivityManager myConnection = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myNetwork = myConnection.getActiveNetworkInfo();
        return myNetwork != null && myNetwork.isConnectedOrConnecting();

    }

    @Override
    public void searchWord(String movie) {

        //This will build out my URL and then execute the async task on the build URL
        String startingURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=tywc7nbpytk9bygpjumc9y8c&q=";
        String endOfURL = "&page_limit=10";

        try {

            URL getURLData = new URL(startingURL + movie + endOfURL);

            new GetMovieData().execute(getURLData);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }

    }

    //this function will be called when there is no network connection that is detected, when no connection is detected a try is called to try and find a save file
    //if nothing is found the catch is called and will display a toast letting the user know there is no information saved.
    public void noNetworkData(){

        MasterFragment frag = (MasterFragment) getFragmentManager().findFragmentById(R.id.master_container);

        frag.listViewUpdate();

            try {

                FileInputStream input = openFileInput(FILENAME);

                ObjectInputStream stream = new ObjectInputStream(input);

                while (input.available() != 0) {

                    mMovieData = (MovieData) stream.readObject();

                    mSaveMovie.add(mMovieData);


                }

                stream.close();

                mMovieArrray = null;

                frag.setListAdapter(new SavedListAdapter(getApplicationContext(), mSaveMovie));

            } catch (Exception e) {

                e.printStackTrace();
                Toast.makeText(this, "No File Found", Toast.LENGTH_SHORT).show();

            }

    }

    //This is the interface used to allow the MasterFragment pass information over and into the DetailFragment
    @Override
    public void movieDetails(String _name, String _rating, Integer _score, String _scoreName, Integer _year) {

        DetailsFragment frag = DetailsFragment.newInstance(_name, _rating, _score, _scoreName, _year);
        getFragmentManager().beginTransaction().replace(R.id.detail_container, frag, DetailsFragment.TAG).commit();

    }

    private class GetMovieData extends AsyncTask <URL, Integer, JSONObject>{

        final String TAG = "Movie Data Fetch";

        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonInfo = "";

            //Try to see if a valid URL has been given and can be connected to
            for (URL getMovieData : urls){

                try {

                    URLConnection connect = getMovieData.openConnection();
                    jsonInfo = IOUtils.toString(connect.getInputStream());
                    break;

                } catch (Exception e) {

                    Log.e (TAG, "Could not establish connection to " + getMovieData.toString());

                }

            }

            JSONObject movieData;
            JSONObject collectedInfo = new JSONObject();

            try {

                mMovieArrray.clear();

                //JSONArray is used because of the format the JSON is in, and then looped through and added into a temp JSONObject, that is then passed into a ArrayList to be used to
                //make the ListView using my custom Adapter
                movieData = new JSONObject(jsonInfo);
                mJSONMovieArray = movieData.getJSONArray("movies");

                for (int i = 0; i < mJSONMovieArray.length(); i++){

                    collectedInfo = mJSONMovieArray.getJSONObject(i);

                    mMovieArrray.add(new Movie(collectedInfo));

                }


            } catch (Exception e){

                Log.e(TAG, "Cannot convert response");
                collectedInfo = null;

            }

            return collectedInfo;
        }

        @Override
        protected void onPostExecute(JSONObject collectedInfo){

            MasterFragment frag = (MasterFragment) getFragmentManager().findFragmentById(R.id.master_container);

            JSONObject tempMovieData;
            new JSONObject();

            //This try will again cycle through the JSONArray and then place the information into a helper class to be parsed and saved to a file.
            try {

                FileOutputStream output = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                ObjectOutputStream stream = new ObjectOutputStream(output);

                for (int i = 0; i < mJSONMovieArray.length(); i++){

                    tempMovieData = mJSONMovieArray.getJSONObject(i);

                    mMovieData = new MovieData(tempMovieData);

                    stream.writeObject(mMovieData);

                }

                stream.close();

            } catch (Exception e){

                e.printStackTrace();

            }

            frag.setListAdapter(new ListAdapter(getApplicationContext(), mMovieArrray));

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle state){

        if (mMovieArrray != null) {

            state.putSerializable("data", mMovieArrray);
            super.onSaveInstanceState(state);

        } else if (mSaveMovie != null){

            state.putSerializable("data", mSaveMovie);
            super.onSaveInstanceState(state);

        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState){

        if (savedState != null){

            if (checkForPref() == false) {

                mMovieArrray = (ArrayList<Movie>) savedState.getSerializable("data");
                MasterFragment frag = (MasterFragment) getFragmentManager().findFragmentById(R.id.master_container);
                frag.setListAdapter(new ListAdapter(getApplicationContext(), mMovieArrray));

            } else {

                mSaveMovie = (ArrayList<MovieData>) savedState.getSerializable("data");
                MasterFragment frag = (MasterFragment) getFragmentManager().findFragmentById(R.id.master_container);
                frag.setListAdapter(new SavedListAdapter(getApplicationContext(), mSaveMovie));

            }

        }

    }

}
