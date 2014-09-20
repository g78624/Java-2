//Kyle Kauck

package com.example.kyle.navigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import Adapters.TeamAdapter;
import Fragments.TeamFragment;
import Helpers.TeamHelper;


public class MainActivity extends Activity {

    private ArrayList<TeamHelper> mTeamData = new ArrayList<TeamHelper>();
    private TeamHelper mTeamDetails = new TeamHelper();
    private DrawerLayout mDrawer;
    private ListView mDrawList;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTeamData = new ArrayList<TeamHelper>();
        mTeamData.add(new TeamHelper("Bombers", "Mark", "Michelle", "Paul", "Jacob", "Allen", "Brent", "Alex", "Bob", "Marty"));
        mTeamData.add(new TeamHelper("Lions", "Stew", "Michael", "Kim", "Tye", "David", "Marlow", "Steven", "Clay", "Eddie"));
        mTeamData.add(new TeamHelper("Sonics", "Dillon", "Andy", "A.J.", "Jerome", "Devon", "Adrian", "Calvin", "Matthew", "Reggie"));
        mTeamData.add(new TeamHelper("Riders", "Kyle", "Krissy", "Bill", "Alex", "Trent", "John", "Marco", "Tristan", "Ben"));
        mTeamData.add(new TeamHelper("Rage", "Tom", "Vince", "Aaron", "Julian", "Jarrod", "Devon", "Darrelle", "Justin", "Danny"));
        mTeamData.add(new TeamHelper("Quake", "Peyton", "Eli", "Joe", "Matt", "Collin", "Carson", "Russell", "Frank", "Dan"));

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawList = (ListView) findViewById(R.id.drawer);

        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);

        mToggle = new ActionBarDrawerToggle(this, mDrawer, R.drawable.ic_action_device_access_storage_1, R.string.open, R.string.close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        mDrawer.setDrawerListener(mToggle);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawList.setAdapter(new TeamAdapter(getApplicationContext(), mTeamData));

        mDrawList.setOnItemClickListener(new DrawerItemClickListener());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        mToggle.syncState();

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            selectedItem(position);

        }

        private void selectedItem(int position) {

            mTeamDetails = mTeamData.get(position);

            Fragment frag = new TeamFragment();
            Bundle args = new Bundle();

            args.putSerializable("team_data", mTeamDetails);
            frag.setArguments(args);

            FragmentManager fragManager = getFragmentManager();
            fragManager.beginTransaction().replace(R.id.content, frag).commit();

            mDrawList.setItemChecked(position, true);
            mDrawer.closeDrawer(mDrawList);

        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean drawer = mDrawer.isDrawerOpen(mDrawList);
        menu.findItem(R.id.action_settings).setVisible(!drawer);

        return super.onPrepareOptionsMenu(menu);

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

        if (mToggle.onOptionsItemSelected(item)){

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

}
