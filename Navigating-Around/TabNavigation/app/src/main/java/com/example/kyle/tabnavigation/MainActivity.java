package com.example.kyle.tabnavigation;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import Adapters.TabAdapter;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    private ViewPager mNewPage;
    private TabAdapter mTabAdapter;
    private ActionBar mActionBar;
    private String[] sections = {"Featured Story", "Recent Stories", "Image Gallery"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This will find the page viewer and then set my adapter to use my custom class for the adapter
        mNewPage = (ViewPager) findViewById(R.id.content);
        mActionBar = getActionBar();
        mTabAdapter = new TabAdapter(getSupportFragmentManager());
        mNewPage.setAdapter(mTabAdapter);

        //This will enable the action bar to use tab navigation
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String name : sections){

            mActionBar.addTab(mActionBar.newTab().setText(name).setTabListener(this));

        }

        mNewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

                mActionBar.setSelectedNavigationItem(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        mNewPage.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

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

            Intent newIntent = new Intent();
            newIntent.setClass(this, PreferencesActivity.class);
            startActivityForResult(newIntent, 10010);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
