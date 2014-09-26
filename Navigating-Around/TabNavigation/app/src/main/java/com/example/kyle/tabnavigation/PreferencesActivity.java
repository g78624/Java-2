//Kyle Kauck

package com.example.kyle.tabnavigation;

import android.app.Activity;
import android.os.Bundle;

import Fragments.SettingsFragment;

public class PreferencesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();

    }
}
