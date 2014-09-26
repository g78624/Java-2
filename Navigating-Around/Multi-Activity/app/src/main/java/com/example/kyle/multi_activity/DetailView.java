//Kyle Kauck

package com.example.kyle.multi_activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Data_and_Adapters.DataHelper;
import Fragments.DetailFragment;

public class DetailView extends Activity {

    private int position;
    private ArrayList<DataHelper> mContactDetail = new ArrayList<DataHelper>();
    private DataHelper mNewDetails = new DataHelper();
    private static final String FILENAME = "contact.txt";
    DataHelper mDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null){

            DetailFragment frag = DetailFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.detail_container, frag, DetailFragment.TAG).commit();

            Intent intent = this.getIntent();
            mContactDetail = (ArrayList<DataHelper>) intent.getSerializableExtra("array");
            position = intent.getIntExtra("position", 0);

            mNewDetails = (DataHelper) intent.getSerializableExtra("contact_details");

        }

    }

    //This is called when the user presses delete, and will delete the item and pass the new array with the deleted information back
    //to the main view and then saves out the file again with the updates
    public void deleteContact(){

        mContactDetail.remove(position);

        Intent intent = new Intent();
        intent.putExtra("array", mContactDetail);

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

    public DataHelper setValues(){

        return mNewDetails;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.detail_menu, menu);

        ActionBar getActionBar = getActionBar();

        getActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#571B7E")));

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() ==  android.R.id.home){

            Intent intent = new Intent();
            intent.putExtra("array", mContactDetail);

            setResult(RESULT_OK, intent);

            finish();

        } else if (item.getItemId() == R.id.callContact){

            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mNewDetails.getPhone()));
            startActivity(intent);

        }

        return false;
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra("array", mContactDetail);

        setResult(RESULT_OK, intent);

        finish();

    }
}
