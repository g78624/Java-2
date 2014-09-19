//Kyle Kauck

package Fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kyle.multi_activity.DetailView;
import com.example.kyle.multi_activity.MainActivity;
import com.example.kyle.multi_activity.R;

import java.util.ArrayList;

import Data_and_Adapters.Adapter;
import Data_and_Adapters.DataHelper;

public class MainFragment extends ListFragment {

    private DataHelper mContactDetails = new DataHelper();
    private ArrayList<DataHelper> mContactArray = new ArrayList<DataHelper>();
    private ArrayList<DataHelper> mContactDetail = new ArrayList<DataHelper>();
    private final int CREATECODE = 101;

    public static final String TAG = "Main Fragment";

    public static MainFragment newInstance(){

        return new MainFragment();

    }

    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceBundle){

        return _inflater.inflate(R.layout.main_view, _container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        final View view = getView();
        assert view != null;

        Toast.makeText(getActivity(), "Saved Data Has Been Loaded", Toast.LENGTH_SHORT).show();

        ((MainActivity) getActivity()).loadSavedData();

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Intent detailView = new Intent(getActivity(), DetailView.class);

        int arrayPosition = position;
        mContactArray = ((MainActivity) getActivity()).getArray();
        mContactDetails = ((MainActivity) getActivity()).getArray().get(position);

        detailView.putExtra("contact_details", mContactDetails);
        detailView.putExtra("array", mContactArray);
        detailView.putExtra("position", arrayPosition);

        startActivityForResult(detailView, CREATECODE);

    }

    public void updateList(){

        setListAdapter(null);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATECODE){

            MainFragment frag = (MainFragment) getFragmentManager().findFragmentById(R.id.main_container);
            frag.updateList();

            mContactDetail = (ArrayList<DataHelper>) data.getSerializableExtra("array");
            frag.setListAdapter(new Adapter(getActivity(), mContactDetail));

        }
    }
}
