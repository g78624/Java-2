//Kyle Kauck

package com.example.kyle.multi_activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Data_and_Adapters.DataHelper;
import Fragments.CreateFragment;

public class CreateView extends Activity implements CreateFragment.contactDetails {

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

        getMenuInflater().inflate(R.menu.create_menu, menu);

        ActionBar getActionBar = getActionBar();

        getActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#571B7E")));

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() ==  android.R.id.home){

            saveContact();

        } else if (item.getItemId() == R.id.action_save){

            CreateFragment frag = (CreateFragment) getFragmentManager().findFragmentById(R.id.create_container);
            frag.createContact();

        } else if (item.getItemId() == R.id.action_reset){

            CreateFragment frag = (CreateFragment) getFragmentManager().findFragmentById(R.id.create_container);
            frag.resetForm();

        }

        return false;
    }

    @Override
    public void onBackPressed() {

        saveContact();

    }

    @Override
    public void details(String _name, String _phone, String _relationship, String _birthday) {

        mContactDetail.add(new DataHelper(_name, _phone, _relationship, _birthday));

    }

    //This will save out the updated array with the new entry and then save out the file to local storage
    private void saveContact() {

        Intent intent = new Intent();

        intent.putExtra("contact_detail", mContactDetail);

        try {

            FileOutputStream output = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream stream = new ObjectOutputStream(output);

            for (int i = 0; i < mContactDetail.size(); i++){


                mDataHelper = mContactDetail.get(i);

                stream.writeObject(mDataHelper);

            }

            stream.close();

        } catch (Exception e){

            e.printStackTrace();

        }

        setResult(RESULT_OK, intent);

        finish();

    }


}
