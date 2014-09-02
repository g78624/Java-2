//Kyle Kauck

package com.example.kyle.java2_fundamentals;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (networkConnection()){

            Toast.makeText(getApplicationContext(), "You Are Connected", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(getApplicationContext(), "You Are Not Connected", Toast.LENGTH_LONG).show();

        }

    }

    protected  boolean networkConnection(){

        ConnectivityManager myConnection = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myNetwork = myConnection.getActiveNetworkInfo();

        return myNetwork != null && myNetwork.isConnectedOrConnecting();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
