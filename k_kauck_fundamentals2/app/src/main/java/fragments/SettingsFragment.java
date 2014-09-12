//Kyle Kauck

package fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.example.kyle.java2_fundamentals.MainActivity;
import com.example.kyle.java2_fundamentals.PreferencesActivity;
import com.example.kyle.java2_fundamentals.R;

public class SettingsFragment extends PreferenceFragment {

    private Context context = new MainActivity();

    @Override
    public void onCreate (Bundle _savedInstanceState){

        super.onCreate(_savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }

    @Override
    public void onActivityCreated(Bundle _savedInstanceState){

        super.onActivityCreated(_savedInstanceState);



        Preference settingClear = findPreference("PREF_DELETE");
        settingClear.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Toast.makeText(getActivity(), "You Are Not Connected", Toast.LENGTH_SHORT).show();

                ((PreferencesActivity) getActivity()).deleteCache();

                return true;
            }

        });

    }

}
