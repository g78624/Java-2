//Kyle Kauck

package com.example.kyle.multi_activity;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import Fragments.CreateFragment;

public class CreateView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceBundle){

        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.create_activity);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceBundle == null){

            CreateFragment frag = CreateFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.create_container, frag, CreateFragment.TAG).commit();

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        ActionBar getActionBar = getActionBar();

        getActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#571B7E")));

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() ==  android.R.id.home){

            saveContact();

        }

        return false;
    }

    private void saveContact() {

        finish();

    }
}
