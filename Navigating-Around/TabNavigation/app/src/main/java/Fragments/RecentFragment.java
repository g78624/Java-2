//Kyle Kauck

package Fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyle.tabnavigation.R;

import java.util.ArrayList;

public class RecentFragment extends ListFragment {

    public static final String TAG = "Recent";
    ArrayList<String> mRecent = new ArrayList<String>();
    ListView mRecentList;

    public RecentFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.recent_layout, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        mRecent.add("Patriots Win Again");
        mRecent.add("Broncos Loose A Close Game");
        mRecent.add("Blue Jays Win");
        mRecent.add("Rain Dampens Spirits In Regina");
        mRecent.add("More Trouble In The World");
        mRecent.add("Another News Story That Sounds Bad");

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mRecent);
        setListAdapter(myAdapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        //super.onListItemClick(l, v, position, id);

        TextView thisWord = (TextView) v;

        Toast.makeText(getActivity(), thisWord.getText().toString(), Toast.LENGTH_LONG).show();

    }
}
