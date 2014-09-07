//Kyle Kauck

package com.example.kyle.java2_fundamentals;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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

            MasterFragment frag = MasterFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.master_container, frag, MasterFragment.TAG).commit();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void searchWord(String movie) {

        String startingURL = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=tywc7nbpytk9bygpjumc9y8c&q=";
        String endOfURL = "&page_limit=10";

        try {

            URL getURLData = new URL(startingURL + movie + endOfURL);

            new GetMovieData().execute(getURLData);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }

    }

    public void noNetworkData(){

            try {

                FileInputStream input = openFileInput(FILENAME);

                ObjectInputStream stream = new ObjectInputStream(input);

                while (input.available() != 0) {

                    mMovieData = (MovieData) stream.readObject();

                    mSaveMovie.add(mMovieData);


                }

                stream.close();

                MasterFragment frag = (MasterFragment) getFragmentManager().findFragmentById(R.id.master_container);

                frag.setListAdapter(new SavedListAdapter(getApplicationContext(), mSaveMovie));

            } catch (Exception e) {

                e.printStackTrace();
                Toast.makeText(this, "No File Found", Toast.LENGTH_SHORT).show();

            }

    }

    public boolean networkConnection(){

        ConnectivityManager myConnection = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myNetwork = myConnection.getActiveNetworkInfo();

        return myNetwork != null && myNetwork.isConnectedOrConnecting();

    }

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

}
