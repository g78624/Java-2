package com.example.kyle.spinneradapter;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import Fragments.Hourly;
import Fragments.MainFragment;
import Fragments.Weekly;


public class MainActivity extends Activity implements ActionBar.OnNavigationListener, MainFragment.cityName {

    final String TAG = "Weather App";
    ActionBar mActionBar;
    private String mCity;
    private String[] navigation = {"Current Conditions", "Hourly Forecast", "Long Range Forecast"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionBar = getActionBar();
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

        mActionBar.setListNavigationCallbacks(new ArrayAdapter<String>(mActionBar.getThemedContext(), android.R.layout.simple_list_item_1, android.R.id.text1, navigation), this);

    }

    @Override
    public void cityNameReturn(String city) {

        mCity = city;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {

        if (itemPosition == 0){

            MainFragment frag = MainFragment.newInstance(mCity);
            getFragmentManager().beginTransaction().replace(R.id.container, frag, MainFragment.TAG).commit();

            Toast.makeText(this, "Current Forecast", Toast.LENGTH_SHORT).show();

        } else if (itemPosition == 1){

            Hourly frag = Hourly.newInstance(mCity);
            getFragmentManager().beginTransaction().replace(R.id.container, frag, Hourly.TAG).commit();

            Toast.makeText(this, "Hourly Forecast", Toast.LENGTH_SHORT).show();

        } else if (itemPosition == 2){

            Weekly frag = Weekly.newInstance(mCity);
            getFragmentManager().beginTransaction().replace(R.id.container, frag, Weekly.TAG).commit();

            Toast.makeText(this, "Long Range Forecast", Toast.LENGTH_SHORT).show();

        }

        return false;
    }
}
