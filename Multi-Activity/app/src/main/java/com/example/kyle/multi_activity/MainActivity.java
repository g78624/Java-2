//Kyle Kauck

package com.example.kyle.multi_activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Data_and_Adapters.Adapter;
import Data_and_Adapters.DataHelper;
import Fragments.MainFragment;


public class MainActivity extends Activity {

    private final int CREATECODE = 102;
    private ArrayList<DataHelper> mContactDetail = new ArrayList<DataHelper>();
    private static final String FILENAME = "contact.txt";
    DataHelper mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){

            MainFragment frag = MainFragment.newInstance();
            getFragmentManager().beginTransaction().replace(R.id.main_container, frag, MainFragment.TAG).commit();

        }
    }

    //This is called once the fragment has been loaded to load in any of the saved data the user may have
    public void loadSavedData() {

        MainFragment frag = (MainFragment) getFragmentManager().findFragmentById(R.id.main_container);

        try{

            FileInputStream input = openFileInput(FILENAME);
            ObjectInputStream stream = new ObjectInputStream(input);

            while (input.available() != 0){

                mData = (DataHelper) stream.readObject();
                mContactDetail.add(mData);

            }

            stream.close();

            frag.setListAdapter(new Adapter(getApplicationContext(), mContactDetail));

        } catch (Exception e) {

            e.printStackTrace();

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

        int id = item.getItemId();

        if (id == R.id.action_add) {

            createContact();

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

    private void createContact() {

        Intent create = new Intent(this, CreateView.class);

        create.putExtra("contact_detail", mContactDetail);

        startActivityForResult(create, CREATECODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATECODE && resultCode == RESULT_OK){

            mContactDetail = (ArrayList<DataHelper>) data.getSerializableExtra("contact_detail");

            MainFragment frag = (MainFragment) getFragmentManager().findFragmentById(R.id.main_container);
            frag.setListAdapter(new Adapter(this, mContactDetail));

        }

    }

    public ArrayList<DataHelper> getArray(){

        return mContactDetail;

    }
}
