//Kyle Kauck

package com.example.kyle.java2_fundamentals;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import fragments.SettingsFragment;

public class PreferencesActivity extends Activity {

    private static final String FILENAME = "movieData.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }

    public void deleteCache(){

        Log.i("Ello Govna", "My Govna");

        getApplicationContext().deleteFile(FILENAME);

    }

}
