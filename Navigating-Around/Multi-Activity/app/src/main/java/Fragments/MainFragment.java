//Kyle Kauck

package Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private contactDetails mDetails;
    private ActionMode mAction;
    private int mContactSelect = -1;

    public static final String TAG = "Main Fragment";

    public static MainFragment newInstance(){

        return new MainFragment();

    }

    public interface contactDetails{

        public void returnNewArray(ArrayList<DataHelper> contactArray);

    }

    @Override
    public void onAttach(Activity activity) {

        super.onAttach(activity);

        if (activity instanceof contactDetails){

            mDetails = (contactDetails) activity;

        }

    }

    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceBundle){

        View view = _inflater.inflate(R.layout.main_view, _container, false);

        ListView contactList = (ListView) view.findViewById(android.R.id.list);
        contactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if(mAction != null){

                    return false;

                }

                mContactSelect = position;

                mAction = getActivity().startActionMode(mActionCallback);

                return true;

            }
        });

        return view;

    }

    private ActionMode.Callback mActionCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.contact_menu, menu);

            return true;

        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

            return false;

        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {

                case R.id.contactDelete:

                    Log.i (TAG, "User: " + mContactSelect);

                    deleteThisContact();

                    mode.finish();

                    return true;

                default:
                    return false;

            }

        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            mAction = null;

        }
    };

    public void deleteThisContact(){

        MainActivity.mContactDetail.remove(mContactSelect);
        ((MainActivity) getActivity()).updateData();
        updateList();

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

        setListAdapter(new Adapter(getActivity(), MainActivity.mContactDetail));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATECODE){



            updateList();

            MainFragment frag = (MainFragment) getFragmentManager().findFragmentById(R.id.main_container);

            mContactDetail = (ArrayList<DataHelper>) data.getSerializableExtra("array");
            mDetails.returnNewArray(mContactDetail);
            frag.setListAdapter(new Adapter(getActivity(), mContactDetail));

        }
    }
}
