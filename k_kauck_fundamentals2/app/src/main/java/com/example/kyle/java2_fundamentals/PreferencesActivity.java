//Kyle Kauck

package com.example.kyle.java2_fundamentals;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import fragments.SettingsFragment;

public class PreferencesActivity extends Activity {

    private static final String FILENAME = "movieData.txt";
    private ArrayList<MovieData> mSaveMovie = new ArrayList<MovieData>();
    MovieData mMovieData;
    private ArrayList<Movie> mMovieArrray = new ArrayList<Movie>();
    SavedListAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }

    /*public void clearCache(Context context){

        try {

            File found = context.getCacheDir();
            if (found != null && found.isDirectory()){

                deleteDatabase(found);

            }

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this, "No File Found", Toast.LENGTH_SHORT).show();

        }

    }

    public static boolean deleteDatabase(File found) {

        if (found != null && found.isDirectory()){

            String[] pieces = found.list();
            for (String piece : pieces) {

                boolean success = deleteDatabase(new File(found, piece));

                if (!success) {

                    return false;

                }

            }

        }

        assert found != null;
        return found.delete();

    }*/

    public void deleteCache(){

        Log.i("Ello Govna", "My Govna");

        getApplicationContext().deleteFile(FILENAME);

        myAdapter = new SavedListAdapter(getApplicationContext(), mSaveMovie);
        myAdapter.notifyDataSetChanged();

    }

    /*public void noNetworkData(){

        try {

            FileInputStream input = openFileInput(FILENAME);

            ObjectInputStream stream = new ObjectInputStream(input);

            while (input.available() != 0) {

                mMovieData = (MovieData) stream.readObject();

                mSaveMovie.add(mMovieData);


            }

            stream.close();

            mMovieArrray = null;

            MasterFragment frag = (MasterFragment) getFragmentManager().findFragmentById(R.id.master_container);

            frag.setListAdapter(new SavedListAdapter(getApplicationContext(), mSaveMovie));

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this, "No File Found", Toast.LENGTH_SHORT).show();

            MasterFragment frag = (MasterFragment) getFragmentManager().findFragmentById(R.id.master_container);

            frag.setListAdapter(new SavedListAdapter(getApplicationContext(), mSaveMovie));

        }

    }*/

}
