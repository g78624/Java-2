//Kyle Kauck

package com.example.kyle.multi_activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import Data_and_Adapters.DataHelper;
import Fragments.CreateFragment;

public class CreateView extends Activity implements CreateFragment.contactDetails {

    private final int CREATECODE = 102;
    private static final String FILENAME = "contact.txt";
    private ArrayList<DataHelper> mContactDetail = new ArrayList<DataHelper>();
    DataHelper mDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){

        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.create_activity);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceBundle == null){

            CreateFragment frag = CreateFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.create_container, frag, CreateFragment.TAG).commit();

            Intent intent = this.getIntent();
            mContactDetail = (ArrayList<DataHelper>) intent.getSerializableExtra("contact_detail");

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

    @Override
    public void details(String _name, String _phone, String _relationship, String _birthday) {

        mContactDetail.add(new DataHelper(_name, _phone, _relationship, _birthday));

        Log.i ("Ello", "This is a break point");

    }

    private void saveContact() {

        Intent intent = new Intent();

        intent.putExtra("contact_detail", mContactDetail);

        setResult(RESULT_OK, intent);

        finish();

    }


}
