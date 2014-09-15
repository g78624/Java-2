package Fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kyle.multi_activity.R;

public class MainFragment extends ListFragment {

    public static final String TAG = "Main Fragment";

    public static MainFragment newInstance(){

        return new MainFragment();

    }

    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceBundle){

        return _inflater.inflate(R.layout.main_view, _container, false);

    }

}
